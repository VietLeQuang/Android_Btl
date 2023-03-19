package com.example.appbantruyen.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.appbantruyen.R;
import com.example.appbantruyen.adapters.CategoryAdapter;
import com.example.appbantruyen.adapters.PopularAdapter;
import com.example.appbantruyen.databinding.ActivityHomeBinding;
import com.example.appbantruyen.databinding.ActivityHomeBindingImpl;
import com.example.appbantruyen.model.Meals;
import com.example.appbantruyen.viewModel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    HomeViewModel homeViewModel;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        try {
            initView();
            initData();


        }catch (NullPointerException ex){
            System.out.println("Exception in NPE1()" + ex);
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
               CategoryAdapter adapter = new CategoryAdapter(categoryModel.getResult());
               binding.rcCategories.setAdapter(adapter);
            }
        });
        homeViewModel.mealModelMutableLiveData(1).observe(this,mealModel->{
            if(mealModel.isSuccess()){
                PopularAdapter adapter=new PopularAdapter(mealModel.getResult());
                binding.rcPopular.setAdapter(adapter);
            }
        });
    }
}