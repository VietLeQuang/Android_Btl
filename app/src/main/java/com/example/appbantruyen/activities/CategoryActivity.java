package com.example.appbantruyen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appbantruyen.R;
import com.example.appbantruyen.adapters.BookAdapter;
import com.example.appbantruyen.databinding.ActivityCategoryBinding;
import com.example.appbantruyen.model.Category;
import com.example.appbantruyen.viewModel.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {

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
        String namecate = getIntent().getStringExtra("namecate");
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        viewModel.mealModelMutableLiveData(idcate).observe(this, mealModel -> {
            if(mealModel.isSuccess())
            {
                BookAdapter adapter = new BookAdapter(mealModel.getResult());
                binding.rcCategory.setAdapter(adapter);
                binding.tvname.setText(namecate + ":"+ mealModel.getResult().size());
            }
        });
    }

    private void initView()
    {
        binding.rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rcCategory.setLayoutManager(layoutManager);
    }
}