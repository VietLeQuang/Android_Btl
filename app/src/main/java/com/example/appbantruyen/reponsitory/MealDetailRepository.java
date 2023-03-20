package com.example.appbantruyen.reponsitory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appbantruyen.model.MealDetail;
import com.example.appbantruyen.model.MealDetailModel;
import com.example.appbantruyen.model.MealModel;
import com.example.appbantruyen.retrofit.FoodAppApi;
import com.example.appbantruyen.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailRepository {
    private FoodAppApi appApi;

    public MealDetailRepository() {
        appApi = RetrofitInstance.getRetrofit().create(FoodAppApi.class);
    }
    public MutableLiveData<MealDetailModel> getMealDetail(int id)
    {
        MutableLiveData<MealDetailModel> data = new MutableLiveData<>();
        appApi.getMealsDetail(id).enqueue(new Callback<MealDetailModel>() {
            @Override
            public void onResponse(Call<MealDetailModel> call, Response<MealDetailModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MealDetailModel> call, Throwable t) {
                data.setValue(null);
                Log.d("log", t.getMessage());
            }
        });
        return data;
    }
}
