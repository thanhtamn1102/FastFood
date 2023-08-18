package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.model.NhomTuyChon;
import com.example.fastfoodpos.model.TuyChon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public class TuyChonItem extends HBox {

    @FXML private TextField txtTenTuyChon, txtDonGia;
    @FXML private SVGPath btnDelete;

    private NhomTuyChon nhomTuyChon;
    private TuyChon tuyChon;

    public TuyChonItem(NhomTuyChon nhomTuyChon, TuyChon tuyChon) {
        this.nhomTuyChon = nhomTuyChon;

        if(nhomTuyChon.getDsTuyChon() == null)
            nhomTuyChon.setDsTuyChon(new ArrayList<>());

        if(tuyChon == null) {
            this.tuyChon = new TuyChon();
            nhomTuyChon.getDsTuyChon().add(this.tuyChon);
        }
        else {
            this.tuyChon = tuyChon;
        }

        init();
        addEvents();
        loadData();
    }

    private void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/tuy-chon-item.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEvents() {
        txtTenTuyChon.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String tenTuyChon) {
                tuyChon.setTenTuyChon(tenTuyChon);
            }
        });

        txtDonGia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String donGia) {
                if(!donGia.isEmpty()) {
                    tuyChon.setGia(Double.parseDouble(donGia));
                }
            }
        });
    }

    private void loadData() {
        txtTenTuyChon.setText(tuyChon.getTenTuyChon());
        txtDonGia.setText(Double.toString(tuyChon.getGia()));
    }

    public SVGPath getBtnDelete() {
        return btnDelete;
    }
}
