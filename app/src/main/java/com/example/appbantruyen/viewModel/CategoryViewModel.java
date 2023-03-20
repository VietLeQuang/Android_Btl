package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.MealModel;
import com.example.appbantruyen.reponsitory.MealRepository;

public class CategoryViewModel extends ViewModel
{
    private MealRepository mealRepository;

    public CategoryViewModel()
    {
        mealRepository = new MealRepository();
    }

    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate)
    {
        return mealRepository.getMeals(idcate);
    }
}
