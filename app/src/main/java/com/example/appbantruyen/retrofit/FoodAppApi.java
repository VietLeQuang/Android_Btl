package com.example.appbantruyen.retrofit;

import com.example.appbantruyen.model.CategoryModel;
import com.example.appbantruyen.model.MealDetailModel;
import com.example.appbantruyen.model.MealModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodAppApi {
    @GET("category.php")
    Call<CategoryModel> getCategory();
    @POST("meal.php")
    @FormUrlEncoded
    Call<MealModel> getMeals(
            @Field("idcate") int idcate
    );

    @POST("mealdetail.php")
    @FormUrlEncoded
    Call<MealDetailModel> getMealsDetail(
            @Field("id") int id
    );
}
