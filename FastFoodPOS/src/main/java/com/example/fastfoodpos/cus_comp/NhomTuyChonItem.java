package com.example.fastfoodpos.cus_comp;

import com.example.fastfoodpos.FastFoodPOS;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.model.NhomTuyChon;
import com.example.fastfoodpos.model.TuyChon;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public class NhomTuyChonItem extends VBox {

    @FXML private Label lblNhomTuyChon;
    @FXML private TextField txtTenNhomTuyChon, txtSoLuongToiDa;
    @FXML private CheckBox ckbChonItNhat1;
    @FXML private Button btnThemTuyChon;
    @FXML private SVGPath btnDelete;
    @FXML private VBox boxTuyChon;

    private MonAn monAn;
    private NhomTuyChon nhomTuyChon;

    public NhomTuyChonItem(MonAn monAn, NhomTuyChon nhomTuyChon) {
        this.monAn = monAn;

        if(monAn.getDsNhomTuyChon() == null)
            monAn.setDsNhomTuyChon(new ArrayList<>());

        if(nhomTuyChon == null) {
            this.nhomTuyChon = new NhomTuyChon();
            monAn.getDsNhomTuyChon().add(this.nhomTuyChon);
        }
        else {
            this.nhomTuyChon = nhomTuyChon;
        }

        init();
        addEvents();
        loadData();
    }

    private void init() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("cus_comp_views/nhom-tuy-chon-item.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addEvents() {
        btnThemTuyChon.setOnMousePressed(mouseEvent -> {
            TuyChonItem tuyChonItem = new TuyChonItem(nhomTuyChon, null);
            tuyChonItem.getBtnDelete().setOnMousePressed(mouseEvent1 -> {
                boxTuyChon.getChildren().remove(tuyChonItem);
            });
            boxTuyChon.getChildren().add(tuyChonItem);
        });

        txtTenNhomTuyChon.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String tenNhomTuyChon) {
                nhomTuyChon.setTenNhomTuyChon(tenNhomTuyChon);
            }
        });

        txtSoLuongToiDa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String soLuongToiDa) {
                if(!soLuongToiDa.isEmpty()) {
                    nhomTuyChon.setSoLuongToiDa(Integer.parseInt(soLuongToiDa));
                }
            }
        });

        ckbChonItNhat1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean chonItNhat1) {
                nhomTuyChon.setBacBuoc(chonItNhat1);
            }
        });
    }

    private void loadData() {

        txtTenNhomTuyChon.setText(nhomTuyChon.getTenNhomTuyChon());
        txtSoLuongToiDa.setText(Integer.toString(nhomTuyChon.getSoLuongToiDa()));
        ckbChonItNhat1.setSelected(nhomTuyChon.isBacBuoc());

        if(nhomTuyChon.getDsTuyChon() != null && nhomTuyChon.getDsTuyChon().size() > 0) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    for(TuyChon tuyChon : nhomTuyChon.getDsTuyChon()) {
                        TuyChonItem tuyChonItem = new TuyChonItem(nhomTuyChon, tuyChon);
                        tuyChonItem.getBtnDelete().setOnMousePressed(mouseEvent1 -> {
                            boxTuyChon.getChildren().remove(tuyChonItem);
                        });
                        boxTuyChon.getChildren().add(tuyChonItem);
                    }
                }
            });
        }
    }

    public SVGPath getBtnDelete() {
        return btnDelete;
    }
}
