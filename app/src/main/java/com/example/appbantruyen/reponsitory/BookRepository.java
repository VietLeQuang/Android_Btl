package com.example.appbantruyen.reponsitory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.appbantruyen.model.BookModel;
import com.example.appbantruyen.retrofit.Api;
import com.example.appbantruyen.retrofit.RetrofitClient;
import com.example.appbantruyen.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private Api api;
    public BookRepository(){
        api = RetrofitClient.getInstance(Utils.BASE_URL).create(Api.class);

    }
    public MutableLiveData<BookModel> getBooks(int idcate){
        MutableLiveData<BookModel> data=new MutableLiveData<>();
        api.getBooks(idcate).enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                Log.d("logg",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
