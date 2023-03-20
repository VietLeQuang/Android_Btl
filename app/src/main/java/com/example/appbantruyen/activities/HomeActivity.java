package com.example.appbantruyen.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.appbantruyen.R;
import com.example.appbantruyen.adapters.CategoryAdapter;
import com.example.appbantruyen.adapters.PopularAdapter;
import com.example.appbantruyen.databinding.ActivityHomeBinding;
import com.example.appbantruyen.databinding.ActivityHomeBindingImpl;
import com.example.appbantruyen.listener.CategoryListener;
import com.example.appbantruyen.listener.EventClickListener;
import com.example.appbantruyen.model.Category;
import com.example.appbantruyen.model.Meals;
import com.example.appbantruyen.retrofit.Api;
import com.example.appbantruyen.retrofit.RetrofitClient;
import com.example.appbantruyen.utils.Utils;
import com.example.appbantruyen.viewModel.HomeViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class HomeActivity extends AppCompatActivity implements CategoryListener, EventClickListener {
    HomeViewModel homeViewModel;
    ActivityHomeBinding binding;
    Api apidangnhap;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        //Kiem tra xem co internet
        apidangnhap = RetrofitClient.getInstance(Utils.BASE_URL).create(Api.class);
        if (isConnected(this)){
            Toast.makeText(getApplication(),"Welcome", Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(getApplication(), "Không có Internet", Toast.LENGTH_SHORT).show();
        }


        try {
            initView();
            initData();


        }catch (NullPointerException ex){
            System.out.println("Exception in NPE1()" + ex);
        }

    }
    //Dang nhap
    private  boolean isConnected (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); // nho them quyen vao khong bi loi
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void initView() {
        binding.rcCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rcCategories.setLayoutManager(layoutManager);

        binding.rcPopular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(this,3);
        binding.rcPopular.setLayoutManager(layoutManager1);
    }

    private void initData() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.categoryModelMutableLiveData().observe(this, categoryModel -> {
            if(categoryModel.isSuccess())
            {
                //Log.d("logg",categoryModel.getResult().get(1).getCategory());
               CategoryAdapter adapter = new CategoryAdapter(categoryModel.getResult(), this);
               binding.rcCategories.setAdapter(adapter);
            }
        });
        homeViewModel.mealModelMutableLiveData(1).observe(this,mealModel->{
            if(mealModel.isSuccess()){
                PopularAdapter adapter = new PopularAdapter(mealModel.getResult(), this);
                binding.rcPopular.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onCategoryClick(Category category) {
        Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
        intent.putExtra("idcate", category.getId());
        intent.putExtra("namecate", category.getCategory());
        startActivity(intent);
    }

    @Override
    public void onPopularClick(Meals meals) {
        Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
        intent.putExtra("id", meals.getIdMeal());
        startActivity(intent);
    }
}