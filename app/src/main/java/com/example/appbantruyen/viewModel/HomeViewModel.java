package com.example.appbantruyen.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appbantruyen.model.CategoryModel;
import com.example.appbantruyen.model.BookModel;
import com.example.appbantruyen.reponsitory.CategoryReponsitory;
import com.example.appbantruyen.reponsitory.BookRepository;

public class HomeViewModel extends ViewModel {
    private CategoryReponsitory categoryReponsitory;
    private BookRepository bookRespository;

    public HomeViewModel() {

        categoryReponsitory = new CategoryReponsitory();
        bookRespository = new BookRepository();
    }
    public MutableLiveData<CategoryModel> categoryModelMutableLiveData(){
        return categoryReponsitory.getCategory();
    }
    public MutableLiveData<BookModel> bookModelMutableLiveData(int idcate) {
        return bookRespository.getBooks(idcate);
    }
}
