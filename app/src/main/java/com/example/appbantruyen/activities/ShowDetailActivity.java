package com.example.appbantruyen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appbantruyen.R;
import com.example.appbantruyen.databinding.ActivityShowDetailBinding;
import com.example.appbantruyen.model.Cart;
import com.example.appbantruyen.model.BookDetail;
import com.example.appbantruyen.utils.Utils;
import com.example.appbantruyen.viewModel.ShowDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;


public class ShowDetailActivity extends AppCompatActivity {
    ShowDetailViewModel viewModel;
    ActivityShowDetailBinding binding;
    int amount = 1;
    BookDetail bookDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        int id = getIntent().getIntExtra("id", 0);
        Paper.init(this);
        getData(id);
        eventClick();
        showToData(id);

        //binding.btnadd.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        //        startActivity(intent);
        //        finish();
        //    }
        //});
    }

    private void showToData(int id) {

        if(Paper.book().read("cart") != null)
        {
            List<Cart> list = Paper.book().read("cart");
            Utils.cartList = list;
        }

        if(Utils.cartList.size() > 0){
            for(int i = 0; i < Utils.cartList.size(); i++)
            {
                if(Utils.cartList.get(i).getBookDetail().getId() == id)
                {
                    binding.tvamount.setText(Utils.cartList.get(i).getAmount() + "");
                }
            }
        }
        else
        {
            binding.tvamount.setText(amount + "");
        }
    }

    private void eventClick() {
        binding.imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = Integer.parseInt(binding.tvamount.getText().toString()) + 1;
                binding.tvamount.setText(String.valueOf(amount));
            }
        });
        binding.imgsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(binding.tvamount.getText().toString()) > 1)
                {
                    amount = Integer.parseInt(binding.tvamount.getText().toString()) - 1;
                    binding.tvamount.setText(String.valueOf(amount));
                }
            }
        });
        binding.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(amount);
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void addToCart(int amount)
    {
        boolean checkExit = false;
        int n = 0;
        if (Utils.cartList != null){
            if(Utils.cartList.size() > 0)
            {
                for(int i = 0; i < Utils.cartList.size(); i++)
                {
                    if(Utils.cartList.get(i).getBookDetail().getId() == bookDetail.getId())
                    {
                        checkExit = true;
                        n = i;
                        break;
                    }
                }
            }
        }else {
            Utils.cartList = new ArrayList<>();
        }


        if(checkExit)
        {
            Utils.cartList.get(n).setAmount(amount);
        }
        else
        {
            Cart cart = new Cart();
            cart.setBookDetail(bookDetail);
            cart.setAmount(amount);
            Utils.cartList.add(cart);
        }

        Toast.makeText(getApplicationContext(), "Added to your cart", Toast.LENGTH_SHORT).show();
        Paper.book().write("cart", Utils.cartList);

    }

    private void getData(int id)
    {
        viewModel = new ViewModelProvider(this).get(ShowDetailViewModel.class);
        viewModel.bookDetailModelMutableLiveData(id).observe(this, bookDetailModel -> {
            if(bookDetailModel.isSuccess())
            {
                bookDetail = bookDetailModel.getResult().get(0);
                Log.d("log", bookDetailModel.getResult().get(0).getBook());
                binding.txtnamefood.setText(bookDetail.getBook());
                binding.txtprice.setText("$"+ bookDetail.getPrice());
                binding.txtdesc.setText(bookDetail.getInstructions());
                Glide.with(this).load(bookDetail.getStrbookthumb()).into(binding.image);

            }
        });
    }
}