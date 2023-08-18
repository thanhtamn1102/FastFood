package com.example.fastfoodpos.controller;

import com.example.fastfoodpos.cus_comp.OrderDetailItemOrderView;
import com.example.fastfoodpos.model.ChiTietDonHang;
import com.example.fastfoodpos.model.DonHang;
import com.example.fastfoodpos.model.DonHangStatus;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.service.DonHangService;
import com.example.fastfoodpos.utils.StringUtils;
import com.example.fastfoodpos.utils.TimeUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import retrofit2.Call;
import retrofit2.Response;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormDonHangController implements Initializable {

    @FXML private Label lblMaDonHang, lblNgayTaoDon, lblTrangThai;
    @FXML private Label lblHoTenKhachHang, lblSoDienThoai, lblDiaChi;
    @FXML private ListView<ChiTietDonHang> lvChiTietDonHang;
    @FXML private Button btnXacNhan, btnHuyDonHang, btnThoat;
    @FXML private Label lblTongTien, lblPhiVanChuyen, lblTongThanhToan;
    @FXML private TextArea txtGhiChu;

    private ObservableList<DonHang> dsDonHang;
    private DonHang donHang;
    private ObservableList<ChiTietDonHang> chiTietDonHangs;

    public FormDonHangController(ObservableList<DonHang> dsDonHang, DonHang donHang) {
        this.dsDonHang = dsDonHang;
        this.donHang = donHang;
        chiTietDonHangs = FXCollections.observableArrayList();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addControls();
        addEvents();
        loadData();
    }

    private void addControls() {
        lvChiTietDonHang.setItems(chiTietDonHangs);
        lvChiTietDonHang.setCellFactory(new Callback<ListView<ChiTietDonHang>, ListCell<ChiTietDonHang>>() {
            @Override
            public ListCell<ChiTietDonHang> call(ListView<ChiTietDonHang> chiTietDonHangListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(ChiTietDonHang chiTietDonHang, boolean b) {
                        super.updateItem(chiTietDonHang, b);
                        if(chiTietDonHang != null) {
                            OrderDetailItemOrderView item = new OrderDetailItemOrderView(chiTietDonHang);

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

        btnXacNhan.setOnMousePressed(mouseEvent -> {
            if(btnXacNhan.getText().equalsIgnoreCase("Xác nhận")) {
                setTrangThaiDonHang(donHang, DonHangStatus.DANG_XU_LY);
                btnXacNhan.setText("Đã giao vận chuyển");
            }
            else if(btnXacNhan.getText().equalsIgnoreCase("Đã giao vận chuyển")) {
                setTrangThaiDonHang(donHang, DonHangStatus.DANG_GIAO_HANG);
                btnXacNhan.setText("Giao thành công");
            }
            else if(btnXacNhan.getText().equalsIgnoreCase("Giao thành công")) {
                donHang.setNgayGiaoHang(TimeUtils.getTimestamp(LocalDateTime.now()));
                setTrangThaiDonHang(donHang, DonHangStatus.DA_HOAN_THANH);
                btnXacNhan.setVisible(false);
                btnHuyDonHang.setVisible(false);
            }
        });

        btnHuyDonHang.setOnMousePressed(mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hủy đơn hàng");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn hủy đơn hàng này không?");

            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK) {
                setTrangThaiDonHang(donHang, DonHangStatus.DA_HUY);
            }
        });
    }

    private void setTrangThaiDonHang(DonHang donHang, DonHangStatus status) {
        DonHangService.api.setTrangThaiDonHang(donHang.getMaDonHang(), status).enqueue(new retrofit2.Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean result = response.body();
                if(result != null && result) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            donHang.setTrangThai(status);
                            dsDonHang.set(dsDonHang.indexOf(donHang), donHang);

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Cập nhật trạng thái đơn hàng");
                            alert.setContentText("Cập nhật trạng thái đơn hàng thành công");
                            alert.setHeaderText(null);
                            alert.show();

                            loadData();
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
                        alert.setTitle("Cập nhật trạng thái đơn hàng");
                        alert.setContentText("Lỗi: Cập nhật trạng thái đơn hàng không thành công");
                        alert.setHeaderText(null);
                        alert.show();

                        loadData();
                    }
                });
            }
        });
    }

    private void loadData() {
        if(donHang != null) {
            lblMaDonHang.setText(donHang.getMaDonHang());

            LocalDateTime ngayTaoDon = TimeUtils.toLocalDateTime(donHang.getNgayTao());
            lblNgayTaoDon.setText(TimeUtils.getStringFromLocalDateTime(ngayTaoDon));

            lblHoTenKhachHang.setText(donHang.getHoTenKhachHang());
            lblSoDienThoai.setText(donHang.getSdtKhachHang());
            lblDiaChi.setText(donHang.getDiaChi().toString());
            lblTongTien.setText(StringUtils.formatCurrency(donHang.getTongTien()));
            lblPhiVanChuyen.setText(StringUtils.formatCurrency(donHang.getPhiApDung()));
            lblTongThanhToan.setText(StringUtils.formatCurrency(donHang.getTongThanhTien()));
            txtGhiChu.setText(donHang.getGhiChu());

            if(donHang.getTrangThai() == DonHangStatus.TAO_MOI_CHO_XAC_NHAN) {
                lblTrangThai.setTextFill(Color.GREEN);
                btnHuyDonHang.setVisible(true);
                btnXacNhan.setVisible(true);
                btnXacNhan.setText("Xác nhận");
            }
            else if(donHang.getTrangThai() == DonHangStatus.DANG_XU_LY) {
                lblTrangThai.setTextFill(Color.ORANGE);
                btnHuyDonHang.setVisible(true);
                btnXacNhan.setVisible(true);
                btnXacNhan.setText("Đã giao vận chuyển");
            }
            else if(donHang.getTrangThai() == DonHangStatus.DANG_GIAO_HANG) {
                lblTrangThai.setTextFill(Color.VIOLET);
                btnHuyDonHang.setVisible(false);
                btnXacNhan.setVisible(true);
                btnXacNhan.setText("Giao thành công");
            }
            else if(donHang.getTrangThai() == DonHangStatus.DA_HOAN_THANH) {
                lblTrangThai.setTextFill(Color.BLUE);
                btnHuyDonHang.setVisible(false);
                btnXacNhan.setVisible(false);
            }
            else if(donHang.getTrangThai() == DonHangStatus.DA_HUY) {
                lblTrangThai.setTextFill(Color.RED);
                btnHuyDonHang.setVisible(false);
                btnXacNhan.setVisible(false);
            }

            lblTrangThai.setText(donHang.getTrangThai().toString());

            if(donHang.getChiTietDonHangs() != null && donHang.getChiTietDonHangs().size() > 0) {
                chiTietDonHangs.setAll(donHang.getChiTietDonHangs());
            }
        }
    }

    private void closeStage(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
