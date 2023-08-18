package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.model.ChiTietDonHang;
import com.example.fastfoodpos.model.DanhMuc;
import com.example.fastfoodpos.model.MonAn;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class MonAnItem extends HBox {

    @FXML private Label lblTenMonAn, lblMaMonAn, lblDanhMuc, lblDonGia, lblTrangThai;
    @FXML private SVGPath btnEdit, btnDelete;
    @FXML private ImageView imvHinhAnhMonAn;


    private MonAn monAn;


    public MonAnItem(MonAn monAn) {
        this.monAn = monAn;

        init();
        addControls();
        addEvents();
        loadData();
    }

    private void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/mon-an-item.fxml"));
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

        if(monAn.getDanhMuc() != null)
            lblDanhMuc.setText(monAn.getDanhMuc().getTenDanhMuc());

        if(monAn.isTrangThai()) {
            lblTrangThai.setText("Hiển thị");
            lblTrangThai.setTextFill(Color.GREEN);
        } else {
            lblTrangThai.setText("Đã ẩn");
            lblTrangThai.setTextFill(Color.RED);
        }

        Image imageNoAvailable = new Image(FastFoodPOS.class.getResource("drawable/no-image_available.png").toString(), true);
        imvHinhAnhMonAn.setImage(imageNoAvailable);

        if(monAn.getDsHinhAnh() != null && monAn.getDsHinhAnh().size() > 0) {
            Image monAnImage = new Image(monAn.getDsHinhAnh().get(0), true);
            monAnImage.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.doubleValue() == 1) {
                        imvHinhAnhMonAn.setImage(monAnImage);
                    }
                }
            });
            monAnImage.errorProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue) {
                        monAnImage.cancel();
                    }
                }
            });
        }
    }

    private void addControls() {

    }

    private void addEvents() {
        btnEdit.setOnMousePressed(mouseEvent -> {

        });

        btnDelete.setOnMousePressed(mouseEvent -> {

        });

        this.setOnMouseEntered(mouseEvent -> {
            this.setStyle("-fx-background-color: #EDEDED; -fx-border-color:  #EDEDED; -fx-border-radius: 10; -fx-background-radius: 10");
        });

        this.setOnMouseExited(mouseEvent -> {
            this.setStyle("-fx-background-color: #FFFFFF; -fx-border-color:  #EDEDED; -fx-border-radius: 10; -fx-background-radius: 10");
        });
    }

    public SVGPath getBtnDelete() {
        return btnDelete;
    }

    public SVGPath getBtnEdit() {
        return btnEdit;
    }
}
