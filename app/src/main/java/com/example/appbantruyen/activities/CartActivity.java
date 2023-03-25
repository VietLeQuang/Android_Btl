package com.example.appbantruyen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbantruyen.R;
import com.example.appbantruyen.adapters.CartAdapter;
import com.example.appbantruyen.databinding.ActivityCartBinding;
import com.example.appbantruyen.databinding.ActivityCartBindingImpl;
import com.example.appbantruyen.listener.ChangeNumListener;
import com.example.appbantruyen.model.Cart;
import com.example.appbantruyen.model.MessModel;
import com.example.appbantruyen.utils.Utils;
import com.example.appbantruyen.viewModel.CartViewModel;
import com.google.gson.Gson;

import java.util.List;

import io.paperdb.Paper;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    CartViewModel viewModel;
    int item;
    Double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        Paper.init(this);
        initView();
        initData();
        totalPrice();
        initControl();
    }
    private void initControl() {
        viewModel.init();
        viewModel.messModelMutableLiveData().observe(this, new Observer<MessModel>() {
            @Override
            public void onChanged(MessModel messModel) {
                if (messModel.isSuccess()){
                    Toast.makeText(getApplicationContext(), messModel.getMessage(), Toast.LENGTH_LONG).show();
                    Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(home);
                    Utils.cartList.clear();
                    Paper.book().delete("cart");
                    finish();
                }
            }
        });

        binding.btncheckout.setOnClickListener(new View.OnClickListener() {
            int iduser = Utils.user_current.getId();
            @Override
            public void onClick(View v) {
                String cart = new Gson().toJson(Utils.cartList);
                Log.d("loggg", cart);
                viewModel.checkOut(iduser, item, price, cart);
            }
        });
    }

    private void initData() {
        List<Cart> carts = Paper.book().read("cart");
        Utils.cartList = carts;
        if (Utils.cartList != null){
            CartAdapter adapter = new CartAdapter(this, Utils.cartList, new ChangeNumListener() {
                @Override
                public void change() {
                    totalPrice();
                }
            });
            binding.recyclecart.setAdapter(adapter);
        }
        else {
            binding.txtmess.setVisibility(View.VISIBLE);
            binding.scrollview.setVisibility(View.INVISIBLE);
        }
    }

    private void totalPrice() {
        item = 0;
        price = 0.0;
        if (Utils.cartList != null){
            for (int i = 0; i < Utils.cartList.size(); i++){
                item = item + Utils.cartList.get(i).getAmount();
            }
            for (int i = 0 ;i < Utils.cartList.size(); i++){
                price = price + (Utils.cartList.get(i).getAmount() * Utils.cartList.get(i).getBookDetail().getPrice());
            }
        }
        binding.txtitem.setText(String.valueOf(item));
        binding.txtprice.setText("$" + String.valueOf(price));
    }

    private void initView() {
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding.recyclecart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclecart.setLayoutManager(layoutManager);
    }
}