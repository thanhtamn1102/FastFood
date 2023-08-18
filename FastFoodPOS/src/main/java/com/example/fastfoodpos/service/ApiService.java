package com.example.fastfoodpos.service;

import com.example.fastfoodpos.model.DanhMuc;
import com.example.fastfoodpos.model.DonHang;
import com.example.fastfoodpos.model.MonAn;
import com.example.fastfoodpos.values.StringValues;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(StringValues.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @GET("fastfood/api/monan")
    Call<List<MonAn>> getAllMonAn();

    @POST("fastfood/api/monan")
    Call<MonAn> addMonAn(@Body MonAn monAn);

    @PUT("fastfood/api/monan")
    Call<MonAn> updateMonAn(@Body MonAn monAn);

    @PUT("fastfood/api/monan/updatemany")
    Call<Void> updateManyMonAn(@Body List<MonAn> dsMonAn);

    @GET("fastfood/api/donhang")
    Call<List<DonHang>> getAllDonHang();

    @GET("fastfood/api/danhmuc")
    Call<List<DanhMuc>> getAllDanhMuc();

    @POST("fastfood/api/danhmuc")
    Call<DanhMuc> addDanhMuc(@Body DanhMuc danhMuc);

    @PUT("fastfood/api/danhmuc")
    Call<DanhMuc> updateDanhMuc(@Body DanhMuc danhMuc);

    @PUT("fastfood/api/danhmuc/updatedsmonan")
    Call<DanhMuc> updateDSMonAn(@Body DanhMuc danhMuc);

    @DELETE("fastfood/api/danhmuc/{maDanhMuc}")
    Call<Boolean> deleteDanhMuc(@Path("maDanhMuc") String maDanhMuc);
}
