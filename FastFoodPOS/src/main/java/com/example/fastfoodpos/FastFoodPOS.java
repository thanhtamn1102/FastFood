package com.example.fastfoodpos;

import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.service.ApiService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FastFoodPOS extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ApiService.apiService.getAllMonAn().enqueue(new Callback<List<MonAn>>() {
            @Override
            public void onResponse(Call<List<MonAn>> call, Response<List<MonAn>> response) {
                List<MonAn> dsMonAn = new ArrayList<>();
                dsMonAn.forEach(monAn -> System.out.println(monAn));
            }

            @Override
            public void onFailure(Call<List<MonAn>> call, Throwable throwable) {

            }
        });

        FXMLLoader fxmlLoader = new FXMLLoader(FastFoodPOS.class.getResource("views/fast-food-pos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(FastFoodPOS.class.getResource("styles/styles.css").toString());
        stage.setTitle("Fast Food POS");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}