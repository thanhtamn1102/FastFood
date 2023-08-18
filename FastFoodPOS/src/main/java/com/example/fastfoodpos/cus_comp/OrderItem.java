package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.controller.FastFoodPOSController;
import com.example.fastfoodpos.model.ChiTietDonHang;
import com.example.fastfoodpos.model.DonHang;
import com.example.fastfoodpos.model.DonHangStatus;
import com.example.fastfoodpos.utils.StringUtils;
import com.example.fastfoodpos.utils.TimeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class OrderItem extends HBox {

    @FXML private Label lblMaDonHang;
    @FXML private Label lblTrangThai, lblKhachHang, lblNgayTaoDonHang, lblTongThanhToan;
    @FXML private ListView<ChiTietDonHang> lvChiTietDonHang;
    @FXML private Button btnXacNhan, btnXemChiTiet, btnHuyDonHang;
    @FXML private Label lblDiaChiGiaoHang, lblGhiChuDonHang;

    private DonHang donHang;
    private ObservableList<ChiTietDonHang> dsChiTietDonHang;

    public OrderItem(DonHang donHang) {
        this.donHang = donHang;

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addControls();
        addEvents();
        loadData();
    }

    private void init() throws MalformedURLException {
        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/order-item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dsChiTietDonHang = FXCollections.observableArrayList();
        for(int i = 0; i < donHang.getChiTietDonHangs().size() && i < 2; i++)
            dsChiTietDonHang.add(donHang.getChiTietDonHangs().get(i));
        lvChiTietDonHang.setItems(dsChiTietDonHang);
        lvChiTietDonHang.setCellFactory(new Callback<ListView<ChiTietDonHang>, ListCell<ChiTietDonHang>>() {
            @Override
            public ListCell<ChiTietDonHang> call(ListView<ChiTietDonHang> chiTietDonHangListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(ChiTietDonHang chiTietDonHang, boolean b) {
                        super.updateItem(chiTietDonHang, b);
                        if(chiTietDonHang != null) {
                            OrderDetailItemOrderView orderDetailItem = new OrderDetailItemOrderView(chiTietDonHang);

                            setGraphic(orderDetailItem);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void addControls() {
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
            btnXacNhan.setVisible(false);
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
    }

    private void addEvents() {

    }

    private void loadData() {
        lblMaDonHang.setText(donHang.getMaDonHang());

        LocalDateTime ngayGiaoHang = TimeUtils.toLocalDateTime(donHang.getNgayGiaoHang());
        if(donHang.getTrangThai() == DonHangStatus.DA_HOAN_THANH) {
            lblTrangThai.setText(donHang.getTrangThai().toString() + "   " + TimeUtils.getStringFromLocalDateTime(ngayGiaoHang));
        } else {
            lblTrangThai.setText(donHang.getTrangThai().toString());
        }

        lblKhachHang.setText(donHang.getHoTenKhachHang() + " - " + donHang.getSdtKhachHang());
        lblDiaChiGiaoHang.setText(donHang.getDiaChi().toString());
        lblGhiChuDonHang.setText(donHang.getGhiChu());

        LocalDateTime ngayTaoDonHang = TimeUtils.toLocalDateTime(donHang.getNgayTao());
        lblNgayTaoDonHang.setText(TimeUtils.getStringFromLocalDateTime(ngayTaoDonHang));
        lblTongThanhToan.setText(StringUtils.formatCurrency(donHang.getTongThanhTien()));
    }

    public Button getBtnHuyDonHang() {
        return btnHuyDonHang;
    }

    public Button getBtnXemChiTiet() {
        return btnXemChiTiet;
    }

    public Button getBtnXacNhan() {
        return btnXacNhan;
    }
}
