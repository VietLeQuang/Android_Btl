package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.CategoryModel;
import com.example.appbantruyen.model.MealModel;
import com.example.appbantruyen.reponsitory.CategoryReponsitory;
import com.example.appbantruyen.reponsitory.MealRepository;

public class HomeViewModel extends ViewModel {
    private CategoryReponsitory categoryReponsitory;
    private MealRepository mealRespository;

    public HomeViewModel() {

        categoryReponsitory = new CategoryReponsitory();
        mealRespository = new MealRepository();
    }
    public MutableLiveData<CategoryModel> categoryModelMutableLiveData(){
        return categoryReponsitory.getCategory();
    }
    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate) {
        return mealRespository.getMeals(idcate);
    }
}
