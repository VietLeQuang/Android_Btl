package com.example.appbantruyen.retrofit;

import com.example.appbantruyen.model.BookDetailModel;
import com.example.appbantruyen.model.BookModel;
import com.example.appbantruyen.model.CategoryModel;
import com.example.appbantruyen.model.MessModel;
import com.example.appbantruyen.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    //Post du lieu dang ki
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );

    //Post du lieu dang nhap
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );
    //Post du lieu quen mat khau
    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> resetPass(
            @Field("email") String email
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<BookModel> search(
            @Field("search") String search
    );
    @GET("category.php")
    Call<CategoryModel> getCategory();

    @GET("popular.php")
    Call<CategoryModel> getPopular();

    @POST("book.php")
    @FormUrlEncoded
    Call<BookModel> getBooks(
            @Field("idcate") int idcate
    );


    @POST("bookdetail.php")
    @FormUrlEncoded
    Call<BookDetailModel> getBooksDetail(
            @Field("id") int id
    );

    @POST("cart.php")
    @FormUrlEncoded
    Call<MessModel> postCart(
            @Field("iduser") int id,
            @Field("amount") int amount,
            @Field("total") double total,
            @Field("detail") String detail
    );
}
