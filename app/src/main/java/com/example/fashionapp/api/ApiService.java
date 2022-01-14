package com.example.fashionapp.api;

import com.example.fashionapp.models.SanPhamRecommend;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson=new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
    ApiService apiService=new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("itemRecommend")
    Call<SanPhamRecommend> getRecommend(@Query("id") int id);
}
