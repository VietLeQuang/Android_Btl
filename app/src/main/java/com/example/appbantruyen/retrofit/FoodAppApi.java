package com.example.appbantruyen.retrofit;

import com.example.appbantruyen.model.CategoryModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodAppApi {
    @GET("category.php")
    Call<CategoryModel> getCategory();
}
