package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.CategoryModel;
import com.example.appbantruyen.reponsitory.CategoryReponsitory;

public class HomeViewModel extends ViewModel {
    private CategoryReponsitory categoryReponsitory;

    public HomeViewModel() {
        categoryReponsitory = new CategoryReponsitory();
    }
    public MutableLiveData<CategoryModel> categoryModelMutableLiveData(){
        return categoryReponsitory.getCategory();
    }
}
