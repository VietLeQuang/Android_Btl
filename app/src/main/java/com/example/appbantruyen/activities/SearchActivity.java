package com.example.appbantruyen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbantruyen.R;
import com.example.appbantruyen.adapters.SearchAdapter;
import com.example.appbantruyen.databinding.ActivitySearchBinding;
import com.example.appbantruyen.listener.SearchListener;
import com.example.appbantruyen.model.Books;
import com.example.appbantruyen.retrofit.Api;
import com.example.appbantruyen.retrofit.RetrofitClient;
import com.example.appbantruyen.utils.Utils;
import com.example.appbantruyen.viewModel.SearchViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements SearchListener {
    ActivitySearchBinding binding;
    SearchViewModel viewModel;
    EditText edtsearch;
    Api apitimkiem;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        initView();

    }
    private void initView()
    {

        apitimkiem = RetrofitClient.getInstance(Utils.BASE_URL).create(Api.class);
        edtsearch = findViewById(R.id.editsearch);
        binding.rcSearch.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rcSearch.setLayoutManager(layoutManager);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0)
                {
                    getDataSearch(charSequence.toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hãy nhập sách bạn muốn tìm",Toast.LENGTH_SHORT).show();
                }
                //
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void getDataSearch(String s)
    {
        compositeDisposable.add(apitimkiem.search(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bookModel ->{
                            if(bookModel.isSuccess()){
                                SearchAdapter adaptersearch = new SearchAdapter(bookModel.getResult(), this);
                                binding.rcSearch.setAdapter(adaptersearch);
                            }

                        },throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    @Override
    public void onSearchClick(Books search) {
        Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
        intent.putExtra("idcate", search.getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}