package com.example.fastfoodpos.controller;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.cus_comp.OrderItem;
import com.example.fastfoodpos.model.DonHang;
import com.example.fastfoodpos.model.DonHangStatus;
import com.example.fastfoodpos.service.ApiService;
import com.example.fastfoodpos.service.DonHangService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PageDonHangController extends VBox {

    @FXML private Button btnReload;
    @FXML private ListView<DonHang> lvDonHang;

    private ObservableList<DonHang> dsDonHang;

    public PageDonHangController() {

        init();
        addControls();
        addEvents();
        loadData();
    }

    private void init() {
        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("pages/page-don-hang.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dsDonHang = FXCollections.observableArrayList();
        lvDonHang.setItems(dsDonHang);
        lvDonHang.setCellFactory(new javafx.util.Callback<ListView<DonHang>, ListCell<DonHang>>() {
            @Override
            public ListCell<DonHang> call(ListView<DonHang> donHangListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(DonHang donHang, boolean b) {
                        super.updateItem(donHang, b);
                        if(donHang != null) {
                            OrderItem orderItem = new OrderItem(donHang);

                            orderItem.getBtnXacNhan().setOnMousePressed(mouseEvent -> {
                                if(orderItem.getBtnXacNhan().getText().equalsIgnoreCase("Xác nhận")) {
                                    xacNhanDonHang(donHang);
                                }
                                else if(orderItem.getBtnXacNhan().getText().equalsIgnoreCase("Đã giao vận chuyển")) {

                                }
                            });

                            orderItem.getBtnXemChiTiet().setOnMousePressed(mouseEvent -> {
                                openFormDonHang(donHang);
                            });

                            orderItem.getBtnHuyDonHang().setOnMousePressed(mouseEvent -> {
                                huyDonHang(donHang);
                            });

                            setGraphic(orderItem);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void addControls() {

    }

    private void addEvents() {
        btnReload.setOnMousePressed(mouseEvent -> {
            loadData();
        });
    }

    private void loadData() {
        ApiService.apiService.getAllDonHang().enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                List<DonHang> result = response.body();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(result != null) {
                            dsDonHang.setAll(result);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Lỗi: Có lỗi trong quá trình lấy danh sách đơn hàng");
                        alert.show();
                    }
                });
            }
        });
    }

    private void huyDonHang(DonHang donHang) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hủy đơn hàng");
        alert.setContentText("Bạn có chắn chắn muốn hủy đơn hàng này không?");
        alert.setHeaderText(null);

        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == ButtonType.OK) {
            DonHangService.api.setTrangThaiDonHang(donHang.getMaDonHang(), DonHangStatus.DA_HUY).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Boolean result = response.body();
                    if(result != null && result) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                donHang.setTrangThai(DonHangStatus.DA_HUY);
                                dsDonHang.set(dsDonHang.indexOf(donHang), donHang);

                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("Hủy đơn hàng thành công");
                                alert.show();
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
                            alert.setTitle("Lỗi");
                            alert.setContentText("Hủy đơn hàng không thành công");
                            alert.show();
                        }
                    });
                }
            });
        }
    }

    private void xacNhanDonHang(DonHang donHang) {
        DonHangService.api.setTrangThaiDonHang(donHang.getMaDonHang(), DonHangStatus.DANG_XU_LY).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean result = response.body();
                if(result != null && result) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            donHang.setTrangThai(DonHangStatus.DANG_XU_LY);
                            dsDonHang.set(dsDonHang.indexOf(donHang), donHang);

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Xác nhận đơn hàng");
                            alert.setHeaderText(null);
                            alert.setContentText("Xác nhận đơn hàng thành công");
                            alert.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Xác nhận đơn hàng");
                        alert.setHeaderText(null);
                        alert.setContentText("Lỗi: Xác nhận đơn hàng không thành công");
                        alert.show();
                    }
                });
            }
        });
    }

    private void openFormDonHang(DonHang donHang) {
        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("views/form-don-hang.fxml"));
        fxmlLoader.setController(new FormDonHangController(dsDonHang, donHang));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(FastFoodPOS.class.getResource("styles/styles.css").toString());
            Stage stage = new Stage();
            stage.setTitle("Đơn hàng");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
