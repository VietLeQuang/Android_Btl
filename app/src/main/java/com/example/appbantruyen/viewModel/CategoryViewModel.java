package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.BookModel;
import com.example.appbantruyen.reponsitory.BookRepository;

public class CategoryViewModel extends ViewModel
{
    private BookRepository bookRepository;

    public CategoryViewModel()
    {
        bookRepository = new BookRepository();
    }

    public MutableLiveData<BookModel> bookModelMutableLiveData(int idcate)
    {
        return bookRepository.getBooks(idcate);
    }
}
