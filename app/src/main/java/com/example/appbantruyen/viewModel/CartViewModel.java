package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.MessModel;
import com.example.appbantruyen.reponsitory.CartRepository;

public class CartViewModel extends ViewModel {
    private CartRepository cartRepository;
    private MutableLiveData<MessModel> mutableLiveData;
    public void init(){
        cartRepository = new CartRepository();
        mutableLiveData = cartRepository.messModelMutableLiveData();
    }
    public  void checkOut(int iduser, int amount, double total, String detail){
        cartRepository.checkOut(iduser, amount, total, detail);
    }
    public  MutableLiveData<MessModel> messModelMutableLiveData(){
        return mutableLiveData;
    }
}