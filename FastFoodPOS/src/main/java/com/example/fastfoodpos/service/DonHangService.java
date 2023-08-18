package com.example.fastfoodpos.service;

import com.example.fastfoodpos.model.DonHangStatus;
import com.example.fastfoodpos.values.StringValues;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DonHangService {

    DonHangService api = new Retrofit.Builder()
            .baseUrl(StringValues.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonHangService.class);

    @PUT("fastfood/api/donhang/settrangthai/{maDonHang}")
    Call<Boolean> setTrangThaiDonHang(@Path("maDonHang") String maDonHang, @Body DonHangStatus status);

}
