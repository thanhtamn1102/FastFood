package com.example.fastfoodpos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;

public class FastFoodPOSController implements Initializable {

    @FXML private VBox menuDonHang, menuMonAn, menuDanhMuc, boxContent;
    @FXML private SVGPath iconMenuDonHang, iconMenuMonAn, iconMenuDanhMuc;
    @FXML private Label lblMenuDonHang, lblMenuMonAn, lblMenuDanhMuc;

    private PageDonHangController pageDonHangController;
    private PageMonAnController pageMonAnController;
    private PageDanhMucController pageDanhMucController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addControls();
        addEvents();
        loadData();
    }

    private void init() {
        pageDonHangController = new PageDonHangController();
        pageMonAnController = new PageMonAnController();
        pageDanhMucController = new PageDanhMucController();
    }

    private void addControls() {

    }

    private void addEvents() {
        menuDonHang.setOnMousePressed(mouseEvent -> {
            boxContent.getChildren().clear();
            boxContent.getChildren().add(pageDonHangController);

            menuMonAn.setStyle("-fx-background-color: white");
            iconMenuMonAn.setFill(Color.web("#bdbdbd"));
            lblMenuMonAn.setTextFill(Color.web("#bdbdbd"));

            menuDanhMuc.setStyle("-fx-background-color: white");
            iconMenuDanhMuc.setFill(Color.web("#bdbdbd"));
            lblMenuDanhMuc.setTextFill(Color.web("#bdbdbd"));

            menuDonHang.setStyle("-fx-background-color:  #FFC107");
            iconMenuDonHang.setFill(Color.WHITE);
            lblMenuDonHang.setTextFill(Color.WHITE);
        });

        menuMonAn.setOnMousePressed(mouseEvent -> {
            boxContent.getChildren().clear();
            boxContent.getChildren().add(pageMonAnController);

            menuDonHang.setStyle("-fx-background-color: white");
            iconMenuDonHang.setFill(Color.web("#bdbdbd"));
            lblMenuDonHang.setTextFill(Color.web("#bdbdbd"));

            menuDanhMuc.setStyle("-fx-background-color: white");
            iconMenuDanhMuc.setFill(Color.web("#bdbdbd"));
            lblMenuDanhMuc.setTextFill(Color.web("#bdbdbd"));

            menuMonAn.setStyle("-fx-background-color:  #FFC107");
            iconMenuMonAn.setFill(Color.WHITE);
            lblMenuMonAn.setTextFill(Color.WHITE);
        });

        menuDanhMuc.setOnMousePressed(mouseEvent -> {
            boxContent.getChildren().clear();
            boxContent.getChildren().add(pageDanhMucController);

            menuMonAn.setStyle("-fx-background-color: white");
            iconMenuMonAn.setFill(Color.web("#bdbdbd"));
            lblMenuMonAn.setTextFill(Color.web("#bdbdbd"));

            menuDanhMuc.setStyle("-fx-background-color: white");
            iconMenuDanhMuc.setFill(Color.web("#bdbdbd"));
            lblMenuDanhMuc.setTextFill(Color.web("#bdbdbd"));

            menuDanhMuc.setStyle("-fx-background-color:  #FFC107");
            iconMenuDanhMuc.setFill(Color.WHITE);
            lblMenuDanhMuc.setTextFill(Color.WHITE);
        });
    }

    private void loadData() {
        boxContent.getChildren().add(pageDonHangController);
    }
}
