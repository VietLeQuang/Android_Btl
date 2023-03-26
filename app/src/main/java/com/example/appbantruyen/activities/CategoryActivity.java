package com.example.appbantruyen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.appbantruyen.R;
import com.example.appbantruyen.adapters.BookAdapter;
import com.example.appbantruyen.adapters.PopularAdapter;
import com.example.appbantruyen.databinding.ActivityCategoryBinding;
import com.example.appbantruyen.model.Category;
import com.example.appbantruyen.listener.EventClickListener;
import com.example.appbantruyen.model.Books;
import com.example.appbantruyen.viewModel.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity implements EventClickListener {

    ActivityCategoryBinding binding;
    CategoryViewModel viewModel;
    Category category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        initView();
        initData();
    }

    private void initData()
    {
        int idcate = getIntent().getIntExtra("idcate", category.getId());

            idcate = getIntent().getIntExtra("idcate", 2);

        String namecate = getIntent().getStringExtra("namecate");
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        viewModel.bookModelMutableLiveData(idcate).observe(this, bookModel -> {
            if(bookModel.isSuccess())
            {
                BookAdapter adapter = new BookAdapter(bookModel.getResult(), this);
                binding.rcCategory.setAdapter(adapter);
                binding.tvname.setText(namecate + ":"+ bookModel.getResult().size());
            }
        });
    }

    private void initView()
    {
        binding.rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rcCategory.setLayoutManager(layoutManager);


    }
    public void onPopularClick(Books books) {
        Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
        intent.putExtra("id", books.getIdBook());
        startActivity(intent);
    }
}