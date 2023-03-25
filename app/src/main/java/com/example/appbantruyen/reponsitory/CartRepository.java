package com.example.appbantruyen.reponsitory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appbantruyen.model.MessModel;
import com.example.appbantruyen.retrofit.Api;
import com.example.appbantruyen.retrofit.RetrofitClient;
import com.example.appbantruyen.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private Api api;
    MutableLiveData<MessModel> data;

    public CartRepository() {
        api = RetrofitClient.getInstance(Utils.BASE_URL).create(Api.class);
        data = new MutableLiveData<>();
    }
    public  void checkOut(int iduser, int amount, double total, String detail){
        api.postCart(iduser,amount,total,detail).enqueue(new Callback<MessModel>() {
            @Override
            public void onResponse(Call<MessModel> call, Response<MessModel> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MessModel> call, Throwable t) {
                data.postValue(null);
                Log.d("loggg", t.getMessage());
            }
        });
    }

    public  MutableLiveData<MessModel> messModelMutableLiveData(){
        return data;
    }
}
