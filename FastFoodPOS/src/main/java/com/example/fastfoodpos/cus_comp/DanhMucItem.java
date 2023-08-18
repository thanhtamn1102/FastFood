package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.model.DanhMuc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

public class DanhMucItem extends HBox {

    @FXML private Label lblTenDanhMuc;
    @FXML private SVGPath btnRemove;

    private DanhMuc danhMuc;

    public DanhMucItem(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;

        init();
        addEvents();
        loadData();
    }

    private void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/danh-muc-item.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEvents() {

    }

    private void loadData() {
        lblTenDanhMuc.setText(danhMuc.getTenDanhMuc());
    }

    public SVGPath getBtnRemove() {
        return btnRemove;
    }
}
