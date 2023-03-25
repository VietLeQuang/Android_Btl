package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.BookDetailModel;
import com.example.appbantruyen.reponsitory.BookDetailRepository;

public class ShowDetailViewModel extends ViewModel {
    private BookDetailRepository bookDetailRepository;

    public ShowDetailViewModel() {
        bookDetailRepository = new BookDetailRepository();

    }

    public MutableLiveData<BookDetailModel> mealDetailModelMutableLiveData(int id)
    {
        return bookDetailRepository.getBookDetail(id);
    }
}
