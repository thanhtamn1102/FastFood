package com.example.fastfoodpos.controller;

import com.example.fastfoodpos.cus_comp.MonAnItem1;
import com.example.fastfoodpos.cus_comp.MonAnItemDanhMucView;
import com.example.fastfoodpos.model.DanhMuc;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.service.ApiService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import retrofit2.Call;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FormThemMonAnVaoDMMA implements Initializable {

    @FXML private TextField txtMaMonAn, txtTenMonAn;
    @FXML private ComboBox<DanhMuc> cbxDanhMuc;
    @FXML private Button btnTimKiem;
    @FXML private Button btnHienThiTatCa;
    @FXML private Button btnReset;
    @FXML private ListView<MonAn> lvKetQuaTimKiem; private ObservableList<MonAn> dsKetQuaTimKiem;
    @FXML private ListView<MonAn> lvMonAnDaChon; private ObservableList<MonAn> dsMonAnDaChon;
    @FXML private Button btnHuy;
    @FXML private Button btnThemVaoDanhMuc;

    private ObservableList<MonAn> dsMonAnTrongDanhMuc;
    private ObservableList<MonAn> dsMonAn1;


    public FormThemMonAnVaoDMMA(ObservableList<MonAn> dsMonAnTrongDanhMuc) {
        this.dsMonAnTrongDanhMuc = dsMonAnTrongDanhMuc;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addControls();
        addEvents();
        loadData();
    }

    private void init() {
        dsKetQuaTimKiem = FXCollections.observableArrayList();
        lvKetQuaTimKiem.setItems(dsKetQuaTimKiem);

        dsMonAn1 = FXCollections.observableArrayList();

        dsMonAnDaChon = FXCollections.observableArrayList();
        lvMonAnDaChon.setItems(dsMonAnDaChon);
    }

    private void addControls() {
        lvKetQuaTimKiem.setCellFactory(new Callback<ListView<MonAn>, ListCell<MonAn>>() {
            @Override
            public ListCell<MonAn> call(ListView<MonAn> sanPhamListView) {
                return new ListCell<MonAn>() {
                    @Override
                    protected void updateItem(MonAn monAn, boolean b) {
                        super.updateItem(monAn, b);
                        if(monAn != null) {
                            MonAnItemDanhMucView item = new MonAnItemDanhMucView(monAn);

                            if(dsMonAnDaChon.contains(monAn)) {
                                item.setStyle("-fx-background-color: #EDEDED; -fx-border-color:  #EDEDED; -fx-border-radius: 10; -fx-background-radius: 10");
                                item.getCbkSelect().setSelected(true);
                            }

                            item.getCbkSelect().selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                                    if(t1) {
                                        if(!dsMonAnDaChon.contains(monAn))
                                            dsMonAnDaChon.add(monAn);
                                    } else {
                                        dsMonAnDaChon.remove(monAn);
                                    }
                                }
                            });

                            setGraphic(item);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        lvMonAnDaChon.setCellFactory(new Callback<ListView<MonAn>, ListCell<MonAn>>() {
            @Override
            public ListCell<MonAn> call(ListView<MonAn> sanPhamListView) {
                return new ListCell<MonAn>() {
                    @Override
                    protected void updateItem(MonAn monAn, boolean b) {
                        super.updateItem(monAn, b);
                        if(monAn != null) {
                            MonAnItem1 item = new MonAnItem1(monAn);

                            item.getBtnDelete().setOnMousePressed(mouseEvent -> {
                                dsMonAnDaChon.remove(monAn);
                            });

                            setGraphic(item);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void addEvents() {
        btnTimKiem.setOnMousePressed(mouseEvent -> {
            String maMonAn = txtMaMonAn.getText().trim();
            String tenMonAn = txtTenMonAn.getText().trim();
            DanhMuc danhMuc = cbxDanhMuc.getSelectionModel().getSelectedItem();

            dsKetQuaTimKiem.setAll(dsMonAn1);

            for (MonAn monAn : dsMonAn1) {
                if(!maMonAn.isEmpty() && !monAn.getMaMonAn().toLowerCase().contains(maMonAn.toLowerCase())) {
                    dsKetQuaTimKiem.remove(monAn);
                }
                if(!monAn.getTenMonAn().toLowerCase().contains(tenMonAn.toLowerCase())) {
                    dsKetQuaTimKiem.remove(monAn);
                }
//                if(danhMuc != null && !monAn.getDsDanhMuc().contains(danhMuc)) {
//                    dsKetQuaTimKiem.remove(monAn);
//                }
            }
        });

        btnHienThiTatCa.setOnMousePressed(mouseEvent -> {
            dsKetQuaTimKiem.setAll(dsMonAn1);
        });

        btnThemVaoDanhMuc.setOnMousePressed(mouseEvent -> {
            if(dsMonAnDaChon.size() > 0) {
                dsMonAnDaChon.forEach(monAn -> {
                    int index = dsMonAnTrongDanhMuc.indexOf(monAn);
                    if(index >= 0) {

                    } else {
                        dsMonAnTrongDanhMuc.add(monAn);
                    }
                });
                closeStage(mouseEvent);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Không có món ăn nào được chọn");
                alert.show();
            }
        });

        btnReset.setOnMousePressed(mouseEvent -> {
            dsKetQuaTimKiem.clear();
        });

        btnHuy.setOnMousePressed(mouseEvent -> {
            closeStage(mouseEvent);
        });
    }

    private void loadData() {
        ApiService.apiService.getAllDanhMuc().enqueue(new retrofit2.Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                List<DanhMuc> result = response.body();
                if(result != null && result.size() > 0) {
                    cbxDanhMuc.setItems(FXCollections.observableArrayList(result));
                }
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable throwable) {

            }
        });

        ApiService.apiService.getAllMonAn().enqueue(new retrofit2.Callback<List<MonAn>>() {
            @Override
            public void onResponse(Call<List<MonAn>> call, Response<List<MonAn>> response) {
                List<MonAn> result = response.body();
                if(result != null && result.size() > 0) {
                    dsMonAn1.setAll(result);
                }
            }

            @Override
            public void onFailure(Call<List<MonAn>> call, Throwable throwable) {

            }
        });
    }

    private void closeStage(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
