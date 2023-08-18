package com.example.fastfoodpos.service;

import com.example.fastfoodpos.values.StringValues;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface MonAnService {

    MonAnService api = new Retrofit.Builder()
            .baseUrl(StringValues.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MonAnService.class);

    @DELETE("fastfood/api/monan/{maMonAn}")
    Call<Boolean> deleteMonAn(@Path("maMonAn") String maMonAn);

}
