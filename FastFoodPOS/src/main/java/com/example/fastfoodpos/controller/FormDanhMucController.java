package com.example.fastfoodpos.controller;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.cus_comp.MonAnItem1;
import com.example.fastfoodpos.cus_comp.MonAnItemDanhMucView;
import com.example.fastfoodpos.model.DanhMuc;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.service.ApiService;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

public class FormDanhMucController extends BorderPane {

    public static final int VIEW = 0;
    public static final int ADD = 1;
    public static final int UPDATE = 2;

    @FXML private TextField txtTenDanhMuc;
    @FXML private Label lblTenDanhMucError;
    @FXML private CheckBox ckbTrangThai;
    @FXML private Button btnLuu;
    @FXML private Button btnThoat;
    @FXML private Button btnCapNhat;
    @FXML private Button btnXoa;
    @FXML private Button btnHuy;
    @FXML private ComboBox<String> cbxNumberSanPhamView;
    @FXML private ListView<MonAn> lvMonAn;
    @FXML private Label lblTitle;
    @FXML private VBox boxSanPham;

    private int type;

    private ObservableList<DanhMuc> categories;
    private ObservableList<MonAn> dsMonAn;
    private DanhMuc danhMuc;

    public FormDanhMucController(ObservableList<DanhMuc> categories, DanhMuc danhMuc, int type) {
        this.categories = categories;

        init(categories);
        addControls();
        addEvents();

        this.danhMuc = danhMuc;
        btnHuy.setVisible(false);

        this.type = type;
        if(type == VIEW) {
            lblTitle.setText("Danh mục món ăn");
            btnLuu.setVisible(false);
            setInputControlEnable(false);
            loadData();
        }
        else if(type == ADD) {
            lblTitle.setText("Thêm Danh mục món ăn");
            btnCapNhat.setVisible(false);
            btnXoa.setVisible(false);
        }
        else if (type == UPDATE) {
            lblTitle.setText("Cập nhật Danh mục món ăn");
            loadData();
            btnCapNhat.setVisible(false);
            btnXoa.setVisible(false);
        }
    }

    private void loadData() {
        txtTenDanhMuc.setText(danhMuc.getTenDanhMuc());
        ckbTrangThai.setSelected(danhMuc.isTrangThai());

        cbxNumberSanPhamView.getItems().addAll("Tất cả", "10", "20", "30", "50", "100");
        cbxNumberSanPhamView.getSelectionModel().select(1);

        if(danhMuc.getDsMonAn() != null && danhMuc.getDsMonAn().size() > 0) {
            dsMonAn.setAll(danhMuc.getDsMonAn());
        }
    }

    private void init(ObservableList<DanhMuc> categories) {
        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("views/form-danh-muc.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        dsMonAn = FXCollections.observableArrayList();
        lvMonAn.setItems(dsMonAn);
    }

    private void addControls() {
        lvMonAn.setCellFactory(new javafx.util.Callback<ListView<MonAn>, ListCell<MonAn>>() {
            @Override
            public ListCell<MonAn> call(ListView<MonAn> monAnListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(MonAn monAn, boolean b) {
                        super.updateItem(monAn, b);
                        if(monAn != null) {
                            MonAnItem1 item = new MonAnItem1(monAn);

                            item.getBtnDelete().setOnMousePressed(mouseEvent -> {
                                dsMonAn.remove(monAn);
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
        btnThoat.setOnMousePressed(mouseEvent -> {
            closeStage(mouseEvent);
        });

        btnLuu.setOnMousePressed(mouseEvent -> {
            String tenDanhMuc = txtTenDanhMuc.getText();
            boolean trangThai = ckbTrangThai.isSelected();

            if(!tenDanhMuc.isEmpty()) {
                DanhMuc danhMuc = new DanhMuc(tenDanhMuc, trangThai);

                danhMuc.getDsMonAn().clear();
                danhMuc.getDsMonAn().addAll(dsMonAn);

                if(type == ADD) {
                    addCategory(danhMuc, mouseEvent);
                }
                else if(type == UPDATE) {
                    danhMuc.setMaDanhMuc(this.danhMuc.getMaDanhMuc());
                    updateCategory(danhMuc, mouseEvent);
                }
            }
            else {
                lblTenDanhMucError.setText("Tên danh mục không được để trống");
            }
        });

        btnXoa.setOnMousePressed(mouseEvent -> {
            deleteCategory(danhMuc, mouseEvent);
            closeStage(mouseEvent);
        });

        btnCapNhat.setOnMousePressed(mouseEvent -> {
            if(btnCapNhat.getText().equalsIgnoreCase("CẬP NHẬT")) {
                lblTitle.setText("Cập nhật Danh mục món ăn");
                btnCapNhat.setText("HỦY CẬP NHẬT"); btnCapNhat.setStyle("-fx-background-color:grey");
                btnLuu.setVisible(true);
                setInputControlEnable(true);
                type = UPDATE;
            }
            else {
                lblTitle.setText("Danh mục món ăn");
                btnCapNhat.setText("CẬP NHẬT"); btnCapNhat.setStyle("-fx-background-color:#0C75F5");
                btnLuu.setVisible(false);
                setInputControlEnable(false);
                type = VIEW;
                loadData();
            }
        });

        txtTenDanhMuc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                lblTenDanhMucError.setText("");
            }
        });

        cbxNumberSanPhamView.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<String>>() {
            @Override
            public void changed(ObservableValue<? extends SingleSelectionModel<String>> observable, SingleSelectionModel<String> oldValue, SingleSelectionModel<String> newValue) {
                if(newValue.toString().equalsIgnoreCase("Tất cả")) {
//                    dsMonAn.setAll();
//                    lvSanPham.setItems(SanPhamDAO.getAllSanPhamFromDanhMuc(danhMuc));
                } else {
                    int n = Integer.parseInt(newValue.toString());
//                    lvSanPham.setItems(SanPhamDAO.getAllSanPhamFromDanhMuc(danhMuc, n));
                }
            }
        });
    }

    private void setInputControlEnable(boolean b) {
        txtTenDanhMuc.setEditable(b);
        ckbTrangThai.setDisable(!b);
    }

    private void closeStage(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void addCategory(DanhMuc danhMuc, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thêm danh mục món ăn");
        alert.setHeaderText(null);

        ApiService.apiService.addDanhMuc(danhMuc).enqueue(new Callback<DanhMuc>() {
            @Override
            public void onResponse(Call<DanhMuc> call, Response<DanhMuc> response) {
                DanhMuc result = response.body();
                if(result != null) {
                    categories.add(result);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            alert.setContentText("Thêm danh mục món ăn thành công");
                            alert.show();
                            closeStage(mouseEvent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DanhMuc> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("Thêm danh mục món ăn không thành công");
                        alert.show();
                    }
                });
            }
        });
    }

    private void deleteCategory(DanhMuc danhMuc, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa danh mục món ăn");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xóa danh mục món ăn này?");

        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == ButtonType.OK) {
            ApiService.apiService.deleteDanhMuc(danhMuc.getMaDanhMuc()).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Boolean result = response.body();
                    if(result != null && result) {
                        categories.remove(danhMuc);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("Xóa danh mục món ăn thành công");
                                alert.show();
                                closeStage(mouseEvent);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable throwable) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setContentText("Xóa danh mục món ăn không thành công");
                            alert.show();
                        }
                    });
                }
            });

        }
    }

    private void updateCategory(DanhMuc danhMuc, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cập nhật danh mục món ăn");
        alert.setHeaderText(null);

        ApiService.apiService.updateDanhMuc(danhMuc).enqueue(new Callback<DanhMuc>() {
            @Override
            public void onResponse(Call<DanhMuc> call, Response<DanhMuc> response) {
                DanhMuc result = response.body();
                if(result != null) {
                    categories.set(categories.indexOf(danhMuc), danhMuc);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            alert.setContentText("Cập nhật danh mục món ăn thành công");
                            alert.show();
                            closeStage(mouseEvent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DanhMuc> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("Cập nhật danh mục món ăn không thành công");
                        alert.show();
                    }
                });
            }
        });


    }
}
