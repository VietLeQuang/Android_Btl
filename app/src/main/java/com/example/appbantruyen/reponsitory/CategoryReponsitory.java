package com.example.appbantruyen.reponsitory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appbantruyen.model.CategoryModel;
import com.example.appbantruyen.retrofit.Api;
import com.example.appbantruyen.retrofit.RetrofitClient;
import com.example.appbantruyen.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryReponsitory {
    private Api bookAppApi;

    public CategoryReponsitory()
    {
        bookAppApi = RetrofitClient.getInstance(Utils.BASE_URL).create(Api.class);
    }
    public MutableLiveData<CategoryModel> getCategory()
    {
        MutableLiveData<CategoryModel> data = new MutableLiveData<>();
        bookAppApi.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.d("logg", t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
