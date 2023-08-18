package com.example.fastfoodpos.controller;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.cus_comp.MonAnItem;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.service.ApiService;
import com.example.fastfoodpos.service.MonAnService;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PageMonAnController extends BorderPane {

    @FXML private ListView<MonAn> listView;
    @FXML private Button btnThemMoi;
    @FXML private TextField txtTimKiem;

    private FilteredList<MonAn> filteredData;
    private ObservableList<MonAn> dsMonAn = FXCollections.observableArrayList();

    public PageMonAnController() {
        init();
        addEvents();
        loadData();
    }

    private void init() {
        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("pages/page-mon-an.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        dsMonAn = FXCollections.observableArrayList();
        listView.setItems(dsMonAn);
        listView.setCellFactory(new Callback<ListView<MonAn>, ListCell<MonAn>>() {
            @Override
            public ListCell<MonAn> call(ListView<MonAn> param) {
                return new ListCell<MonAn>() {
                    @Override
                    protected void updateItem(MonAn monAn, boolean empty) {
                        super.updateItem(monAn, empty);
                        if(monAn != null) {
                            MonAnItem monAnItem = new MonAnItem(monAn);

                            monAnItem.setOnMouseClicked(mouseEvent -> {
                                if(mouseEvent.getClickCount() == 2) {
                                    openFormMonAn(monAn, FormMonAnController.VIEW);
                                }
                            });

                            monAnItem.getBtnEdit().setOnMouseClicked(mouseEvent -> {
//                                openFormEdit(monAnItem);
                            });

                            monAnItem.getBtnDelete().setOnMouseClicked(mouseEvent -> {
                                deleteMonAn(monAn);
                            });

                            setGraphic(monAnItem);
                            setPadding(new Insets(6, 12, 6, 12));
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void addEvents() {

        btnThemMoi.setOnMousePressed(mouseEvent -> {
            openFormMonAn(null, FormMonAnController.ADD);
        });

        filteredData = new FilteredList<MonAn>(dsMonAn, p -> true);
        txtTimKiem.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                searchMonAn(newValue);
            }
        });
        listView.setItems(filteredData);
    }

    private void loadDataToListView(ObservableList<MonAn> monAns) {
        listView.getItems().clear();
        listView.setItems(monAns);
    }

    private void loadData() {
        ApiService.apiService.getAllMonAn().enqueue(new retrofit2.Callback<List<MonAn>>() {
            @Override
            public void onResponse(Call<List<MonAn>> call, Response<List<MonAn>> response) {
                List<MonAn> result = response.body();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(result != null && result.size() > 0) {
                            dsMonAn.setAll(result);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<MonAn>> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Đã có lỗi xảy ra vui lòng thử lại sau");
                        alert.show();
                    }
                });
            }
        });
    }

    private void deleteMonAn(MonAn monAn) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa món ăn");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xóa món ăn này không?");

        Optional<ButtonType> optional = alert.showAndWait();

        if(optional.get() == ButtonType.OK) {
            MonAnService.api.deleteMonAn(monAn.getMaMonAn()).enqueue(new retrofit2.Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Boolean result = response.body();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(result) {
                                dsMonAn.remove(monAn);
                                alert.setContentText("Xóa món ăn thành công");
                                alert.show();
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable throwable) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setContentText("Xóa món ăn không thành công");
                            alert.show();
                        }
                    });
                }
            });
        }
    }

    private void openFormMonAn(MonAn monAn, int type) {
        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("views/form-mon-an.fxml"));
        fxmlLoader.setController(new FormMonAnController(dsMonAn, monAn, type));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        scene.getStylesheets().add(FastFoodPOS.class.getResource("styles/styles.css").toString());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Món ăn");
        stage.show();
    }

    private void searchMonAn(String findString) {
        filteredData.setPredicate(monAn -> {
            if(findString == null || findString.isEmpty()) {
                return true;
            }

            if(monAn.getTenMonAn().toLowerCase().contains(findString.toLowerCase()))
                return true;

            return false;
        });
    }

}
