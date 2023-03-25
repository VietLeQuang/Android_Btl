package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.BookModel;
import com.example.appbantruyen.reponsitory.BookRepository;

public class SearchViewModel extends ViewModel {
    private BookRepository bookRespository;

    public SearchViewModel() {
        bookRespository = new BookRepository();
    }

    public MutableLiveData<BookModel> bookModelMutableLiveData(int idcate) {
        return bookRespository.getBooks(idcate);
    }
}
