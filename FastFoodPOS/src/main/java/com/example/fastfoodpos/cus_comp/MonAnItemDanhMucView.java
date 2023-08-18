package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.model.DanhMuc;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.utils.StringUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonAnItemDanhMucView extends HBox {

    @FXML
    private Label lblTenMonAn;
    @FXML private Label lblMaMonAn;
    @FXML private Label lblDanhMuc;
    @FXML private Label lblTrangThai;
    @FXML private Label lblDonGia;
    @FXML private ImageView imageView;
    @FXML private CheckBox ckbSelect;


    private MonAn monAn;

    public MonAnItemDanhMucView(MonAn monAn) {
        this.monAn = monAn;

        init();
        addControls();
        addEvents();
        loadData();
    }

    private void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/mon-an-item-danh-muc-view.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadData() {
        lblTenMonAn.setText(monAn.getTenMonAn());
        lblMaMonAn.setText(monAn.getMaMonAn());
        lblDonGia.setText(StringUtils.formatCurrency(monAn.getGia()));
        lblTrangThai.setText(monAn.isTrangThai() ? "Hiển thị" : "Đã ẩn");

        lblDanhMuc.setText(monAn.getDanhMuc().getTenDanhMuc());

        Image imageNoAvailable = new Image(FastFoodPOS.class.getResource("drawable/no-image_available.png").toString(), true);
        imageView.setImage(imageNoAvailable);

        if(monAn.getDsHinhAnh() != null && monAn.getDsHinhAnh().size() > 0) {
            Image productImage = new Image(monAn.getDsHinhAnh().get(0), true);
            productImage.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.doubleValue() == 1) {
                        imageView.setImage(productImage);
                    }
                }
            });
            productImage.errorProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue) {
                        productImage.cancel();
                    }
                }
            });
        }
    }

    private void addControls() {

    }

    private void addEvents() {
//        lblTenSanPham.setOnMouseEntered(mouseEvent -> {
//            lblTenSanPham.setTextFill(Color.web("#1111cc"));
//            lblTenSanPham.setUnderline(true);
//        });
//
//        lblTenSanPham.setOnMouseExited(mouseEvent -> {
//            lblTenSanPham.setTextFill(Color.web("#58585a"));
//            lblTenSanPham.setUnderline(false);
//        });

//        this.setOnMouseEntered(mouseEvent -> {
//            this.setStyle("-fx-background-color: #EDEDED; -fx-border-color:  #EDEDED; -fx-border-radius: 10; -fx-background-radius: 10");
//        });
//
//        this.setOnMouseExited(mouseEvent -> {
//            this.setStyle("-fx-background-color: #FFFFFF; -fx-border-color:  #EDEDED; -fx-border-radius: 10; -fx-background-radius: 10");
//        });
    }

    public CheckBox getCbkSelect() {
        return ckbSelect;
    }
}
