package com.example.fastfoodpos.controller;

import com.example.fastfoodpos.cus_comp.DanhMucItem;
import com.example.fastfoodpos.cus_comp.MonAnItem;
import com.example.fastfoodpos.cus_comp.NhomTuyChonItem;
import com.example.fastfoodpos.model.DanhMuc;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.model.NhomTuyChon;
import com.example.fastfoodpos.service.ApiService;
import com.example.fastfoodpos.service.MonAnService;
import com.example.fastfoodpos.utils.StringUtils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormMonAnController implements Initializable {

    public static final int VIEW = 0;
    public static final int ADD = 1;
    public static final int UPDATE = 2;
    private int type;

    @FXML private Label lblMaMonAn;
    @FXML private HBox boxHinhAnhMonAn;
    @FXML private TextField txtURLHinhAnh;
    @FXML private Button btnLoadImage, btnChonHinhAnhTuMayTinh;
    @FXML private TextField txtTenMonAn, txtDonGia;
    @FXML private Label lblTenMonAnError, lblDonGiaError, lblDanhMucError;
    @FXML private CheckBox ckbTrangThai;
    @FXML private ComboBox<DanhMuc> cbxDanhMuc;
    @FXML private HBox boxDanhMuc;
    @FXML private TextArea txtMoTa;
    @FXML private VBox boxNhomTuyChon;
    @FXML private Button btnThemNhomTuyChon;
    @FXML private Button btnLuu, btnThoat, btnCapNhat, btnXoa;

    private ObservableList<MonAn> dsMonAn;
    private MonAn monAn;
    private ObservableList<DanhMuc> dsDanhMuc;
    private List<String> dsHinhAnh = new ArrayList<>();


    public FormMonAnController(ObservableList<MonAn> dsMonAn, MonAn monAn, int type) {
        this.dsMonAn = dsMonAn;
        this.type = type;

        if(monAn == null) {
            this.monAn = new MonAn();
        } else {
            try {
                this.monAn = monAn.clone();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dsDanhMuc = FXCollections.observableArrayList();
        cbxDanhMuc.setItems(dsDanhMuc);

        addEvents();
        loadData();

        if(type == VIEW) {
//            setInputEditable(false);
            btnLuu.setVisible(false);
        }
        else if(type == ADD) {
            btnCapNhat.setVisible(false);
            btnXoa.setVisible(false);
//            boxAddImage.getChildren().add(boxAddImageBody);
//            loadDanhMucSanPhamToCombobox();
        }
        else if (type == UPDATE) {
            btnCapNhat.setVisible(false);
            btnXoa.setVisible(false);
//            boxAddImage.getChildren().add(boxAddImageBody);
//            loadDanhMucSanPhamToCombobox();
        }
    }

    private void addEvents() {
        btnLoadImage.setOnMousePressed(mouseEvent -> {
            String imageLink = txtURLHinhAnh.getText();
            addImageToBoxImage(imageLink);
            dsHinhAnh.add(imageLink);
        });

        btnThemNhomTuyChon.setOnMousePressed(mouseEvent -> {
            NhomTuyChonItem nhomTuyChonItem = new NhomTuyChonItem(monAn, null);
            nhomTuyChonItem.getBtnDelete().setOnMousePressed(mouseEvent1 -> {
                boxNhomTuyChon.getChildren().remove(nhomTuyChonItem);
            });
            boxNhomTuyChon.getChildren().add(nhomTuyChonItem);
        });

        btnThoat.setOnMousePressed(mouseEvent -> {
            closeStage(mouseEvent);
        });

        btnCapNhat.setOnMouseClicked(mouseEvent -> {
            if(btnCapNhat.getText().equalsIgnoreCase("Cập nhật")) {
                btnCapNhat.setText("Hủy cập nhật"); btnCapNhat.setStyle("-fx-background-color: #C4C4C4");
                btnLuu.setVisible(true);
                btnXoa.setVisible(false);
//                setInputEditable(true);
//                boxAddImage.getChildren().add(boxAddImageBody);
                type = UPDATE;
            }
            else {
                btnCapNhat.setText("Cập nhật"); btnCapNhat.setStyle("-fx-background-color: #0C75F5");
                btnLuu.setVisible(false);
                btnXoa.setVisible(true);
//                setInputEditable(false);
//                boxAddImage.getChildren().remove(boxAddImageBody);
                type = VIEW;
                loadData();
            }
        });

        btnLuu.setOnMousePressed(mouseEvent -> {
            String tenMonAn = txtTenMonAn.getText().trim();
            String donGia = txtDonGia.getText().replace(",", "");
            String moTa = txtMoTa.getText();
            boolean trangThai = ckbTrangThai.isSelected();
            DanhMuc danhMuc = cbxDanhMuc.getSelectionModel().getSelectedItem();

            if(checkInput(tenMonAn, donGia, danhMuc)) {
                monAn.setTenMonAn(tenMonAn);
                monAn.setGia(Double.parseDouble(donGia));
                monAn.setMoTa(moTa);
                monAn.setDsHinhAnh(dsHinhAnh);
                monAn.setTrangThai(trangThai);
                monAn.setDanhMuc(danhMuc);
                monAn.setDsDanhGia(null);
                System.out.println(monAn);

                if (type == ADD) {
                    addMonAn(monAn, mouseEvent);
                }
                else if (type == UPDATE) {
                    updateMonAn(monAn, mouseEvent);
                }
            }
        });

        btnXoa.setOnMousePressed(mouseEvent -> {
            deleteMonAn(monAn, mouseEvent);
        });

        txtDonGia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lblDonGiaError.setText("");

                String numberString = newValue.replace(",", "");
                txtDonGia.setText(StringUtils.formatCurrency(Double.parseDouble(numberString)));
            }
        });

        txtDonGia.setOnKeyPressed(keyEvent -> {
            String keyChar = keyEvent.getCode().getChar();
            if(keyChar.matches("[0-9]") || keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                txtDonGia.setEditable(true);
            }
            else {
                txtDonGia.setEditable(false);
            }
        });
    }

    private boolean checkInput(String tenMonAn, String donGia, DanhMuc danhMuc) {
        setLabelErrorEmpty();

        boolean result = true;

        if(tenMonAn.isEmpty()) {
            lblTenMonAnError.setText("Tên món ăn không được để trống");
            result = false;
        }

        if(donGia.isEmpty()) {
            lblDonGiaError.setText("Đơn giá không được để trống");
            result = false;
        }
        else if(Double.parseDouble(donGia) <= 0) {
            lblDonGiaError.setText("Đơn giá phải là số nguyên dương");
        }

        if(dsDanhMuc == null) {
            lblDanhMucError.setText("Danh mục không được để trống");
            result = false;
        }

        return result;
    }

    private void setLabelErrorEmpty() {
        lblTenMonAnError.setText("");
        lblDonGiaError.setText("");
    }

    private void loadData() {
        ApiService.apiService.getAllDanhMuc().enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                List<DanhMuc> result = response.body();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(result != null && result.size() > 0) {
                            dsDanhMuc.setAll(result);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable throwable) {
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

        if(monAn != null) {
            lblMaMonAn.setText(monAn.getMaMonAn());
            txtTenMonAn.setText(monAn.getTenMonAn());
            txtDonGia.setText(StringUtils.formatCurrency(monAn.getGia()));
            txtMoTa.setText(monAn.getMoTa());
            ckbTrangThai.setSelected(monAn.isTrangThai());

            if(monAn.getDsHinhAnh() != null && monAn.getDsHinhAnh().size() > 0) {
                dsHinhAnh.addAll(monAn.getDsHinhAnh());
                monAn.getDsHinhAnh().forEach(hinhAnh -> {
                    addImageToBoxImage(hinhAnh);
                });
            }

            if(monAn.getDanhMuc() != null) {
                cbxDanhMuc.getSelectionModel().select(monAn.getDanhMuc());
            }

            if(monAn.getDsNhomTuyChon() != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for(NhomTuyChon nhomTuyChon : monAn.getDsNhomTuyChon()) {
                            NhomTuyChonItem nhomTuyChonItem = new NhomTuyChonItem(monAn, nhomTuyChon);
                            boxNhomTuyChon.getChildren().add(nhomTuyChonItem);
                        }
                    }
                });
            }

            System.out.println(monAn);
        }
    }

    private void closeStage(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void addImageToBoxImage(String url) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setImage(new Image(url, true));
        boxHinhAnhMonAn.getChildren().add(imageView);
    }

    private void addMonAn(MonAn monAn, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thêm món ăn");
        alert.setHeaderText(null);

        ApiService.apiService.addMonAn(monAn).enqueue(new Callback<MonAn>() {
            @Override
            public void onResponse(Call<MonAn> call, Response<MonAn> response) {
                MonAn result = response.body();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(result != null && dsMonAn.add(result)) {
                            alert.setContentText("Thêm món ăn thành công");
                            alert.show();
                            closeStage(mouseEvent);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<MonAn> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("Thêm món ăn không thành công");
                        alert.show();
                    }
                });
            }
        });
    }

    private void updateMonAn(MonAn monAn, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cập nhật món ăn");
        alert.setHeaderText(null);

        ApiService.apiService.updateMonAn(monAn).enqueue(new Callback<MonAn>() {
            @Override
            public void onResponse(Call<MonAn> call, Response<MonAn> response) {
                MonAn result = response.body();
                if(result != null) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            dsMonAn.set(dsMonAn.indexOf(result), result);
                            alert.setContentText("Cập nhật món ăn thành công");
                            alert.show();
                            closeStage(mouseEvent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MonAn> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("Đã có lỗi xảy ra vui lòng thử lại sau");
                        alert.show();
                    }
                });
            }
        });
    }

    private void deleteMonAn(MonAn monAn, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Xóa món ăn");

        Optional<ButtonType> optional = alert.showAndWait();

        if(optional.get() == ButtonType.OK) {
            MonAnService.api.deleteMonAn(monAn.getMaMonAn()).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Boolean result = response.body();
                    if(result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                dsMonAn.remove(monAn);
                                alert.setContentText("Xóa món ăn thành công");
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
                            alert.setContentText("Xóa món ăn không thành công");
                            alert.show();
                        }
                    });
                }
            });
        }
    }
}
