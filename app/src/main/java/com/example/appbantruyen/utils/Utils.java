package com.example.appbantruyen.utils;

import com.example.appbantruyen.model.Cart;
import com.example.appbantruyen.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String BASE_URL ="http://192.168.1.12/dulieu/";

    public  static User user_current = new User();

    public static List<Cart> cartList = new ArrayList<>();
}
