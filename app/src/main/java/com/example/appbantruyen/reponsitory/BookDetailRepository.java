package com.example.appbantruyen.reponsitory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appbantruyen.model.BookDetailModel;
import com.example.appbantruyen.retrofit.Api;
import com.example.appbantruyen.retrofit.RetrofitClient;
import com.example.appbantruyen.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailRepository {
    private Api appApi;

    public BookDetailRepository() {
        appApi = RetrofitClient.getInstance(Utils.BASE_URL).create(Api.class);
    }
    public MutableLiveData<BookDetailModel> getBookDetail(int id)
    {
        MutableLiveData<BookDetailModel> data = new MutableLiveData<>();
        appApi.getBooksDetail(id).enqueue(new Callback<BookDetailModel>() {
            @Override
            public void onResponse(Call<BookDetailModel> call, Response<BookDetailModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BookDetailModel> call, Throwable t) {
                data.setValue(null);
                Log.d("log", t.getMessage());
            }
        });
        return data;
    }
}
