module com.example.fastfoodpos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires retrofit;
    requires converter.gson;


    opens com.example.fastfoodpos to javafx.fxml;
    exports com.example.fastfoodpos;

    exports com.example.fastfoodpos.controller;
    opens com.example.fastfoodpos.controller to javafx.fxml;

    opens com.example.fastfoodpos.model;
    exports com.example.fastfoodpos.model;

    exports com.example.fastfoodpos.cus_comp;
    opens com.example.fastfoodpos.cus_comp;
}