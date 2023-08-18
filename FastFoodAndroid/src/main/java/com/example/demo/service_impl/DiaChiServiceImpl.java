package com.example.demo.service_impl;

import com.example.demo.model.DiaChi;
import com.example.demo.model.places_service_model.Root;
import com.example.demo.service.DiaChiService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DiaChiServiceImpl implements DiaChiService {

    private DiaChi diaChiFastFood = new DiaChi(
            "FastFood",
            "12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh", "",
            Arrays.asList(10.822271, 106.687482));

    @Override
    public List<DiaChi> timKiemDiaChi(String text) {
        List<DiaChi> result = new ArrayList<>();
        try {
            String uri = "https://places.demo.api.here.com/places/v1/discover/search?q=" + text.replace(" ", "%20") + "&Geolocation=geo%3A52.531%2C13.3843&Accept-Language=vi-VN&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg";
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            StringBuilder sb = new StringBuilder();
            for(int ch; (ch = isr.read()) != -1;) {
                sb.append((char)ch);
            }

            Gson gson = new Gson();
            Root root = gson.fromJson(sb.toString(), Root.class);
            root.getResults().getItems().forEach(item -> {
                DiaChi diaChi = new DiaChi(item.getTitle(), item.getAddress().getText().replace("<br/>", ", "), "", item.getPosition());
                result.add(diaChi);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public double getDistance(DiaChi diaChi) {
        return distance(diaChiFastFood.getPosition().get(0), diaChiFastFood.getPosition().get(1), diaChi.getPosition().get(0), diaChi.getPosition().get(1));
    }

    private double distance(double lat1, double long1, double lat2, double long2) {
        double longDiff = long1 - long2;
        double distance = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(longDiff));
        distance = Math.acos(distance);
        distance = rag2deg(distance);
        distance = distance  * 60 * 1.1515;
        distance = distance * 1.609344;
        return distance;
    }

    private double rag2deg(double distance) {
        return (distance * 180.0 / Math.PI);
    }

    private double deg2rad(double lat1) {
        return (lat1 * Math.PI / 180.0);

    }
}
