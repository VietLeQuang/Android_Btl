package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.MealDetailModel;
import com.example.appbantruyen.reponsitory.MealDetailRepository;

public class ShowDetailViewModel extends ViewModel {
    private MealDetailRepository mealDetailRepository;

    public ShowDetailViewModel() {
        mealDetailRepository = new MealDetailRepository();

    }

    public MutableLiveData<MealDetailModel> mealDetailModelMutableLiveData(int id)
    {
        return mealDetailRepository.getMealDetail(id);
    }
}
