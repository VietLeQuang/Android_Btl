package com.example.appbantruyen.reponsitory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appbantruyen.model.MealModel;
import com.example.appbantruyen.retrofit.FoodAppApi;
import com.example.appbantruyen.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {
    private FoodAppApi api;
    public MealRepository(){
        api = RetrofitInstance.getRetrofit().create(FoodAppApi.class);

    }
    public MutableLiveData<MealModel> getMeals(int idcate){
        MutableLiveData<MealModel> data=new MutableLiveData<>();
        api.getMeals(idcate).enqueue(new Callback<MealModel>() {
            @Override
            public void onResponse(Call<MealModel> call, Response<MealModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MealModel> call, Throwable t) {
                Log.d("logg",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
