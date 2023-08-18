package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.model.ChiTietDonHang;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.model.TuyChonWithSoLuong;
import com.example.fastfoodpos.utils.StringUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrderDetailItemOrderView extends HBox {

    @FXML
    private Label lblTenSanPham;
    @FXML private Label lblMaSanPham;
    @FXML private Label lblDonGia;
    @FXML private Label lblSoLuong;
    @FXML private ImageView imageView;
    @FXML private Label lblDSTuyChon;
    @FXML private Label lblThanhTien;

    private MonAn monAn;
    private ChiTietDonHang chiTietDonHang;

    public OrderDetailItemOrderView(ChiTietDonHang chiTietDonHang) {
        this.chiTietDonHang = chiTietDonHang;
        monAn = chiTietDonHang.getMonAn();

        init();
        addEvents();
        loadData();
    }

    private void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/order-detail-item-order-view.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadData() {
        lblTenSanPham.setText(monAn.getTenMonAn());
        lblMaSanPham.setText(monAn.getMaMonAn());

        double donGia = monAn.getGia();
        for(TuyChonWithSoLuong tuyChonWithSoLuong : chiTietDonHang.getDsTuyChon()) {
            donGia += tuyChonWithSoLuong.getSoLuong() * tuyChonWithSoLuong.getTuyChon().getGia();
        }
        lblDonGia.setText(StringUtils.formatCurrency(donGia));
        lblSoLuong.setText(Integer.toString(chiTietDonHang.getSoLuong()));
        lblThanhTien.setText(StringUtils.formatCurrency(donGia * chiTietDonHang.getSoLuong()));

        String dsTuyChon = "";
        for(TuyChonWithSoLuong tuyChonWithSoLuong : chiTietDonHang.getDsTuyChon()) {
            dsTuyChon += tuyChonWithSoLuong.getSoLuong() + "x  " + tuyChonWithSoLuong.getTuyChon().getTenTuyChon() + "\n";
        }
        lblDSTuyChon.setText(dsTuyChon);

        Image imageNoAvailable = new Image(FastFoodPOS.class.getResource("drawable/no-image_available.png").toString(), true);
        imageView.setImage(imageNoAvailable);

        if(monAn.getDsHinhAnh() != null) {
            Image sanPhamImage = new Image(monAn.getDsHinhAnh().get(0), true);
            sanPhamImage.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.doubleValue() == 1) {
                        imageView.setImage(sanPhamImage);
                    }
                }
            });
            sanPhamImage.errorProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue) {
                        sanPhamImage.cancel();
                    }
                }
            });
        }
    }

    private void addEvents() {

    }

}
