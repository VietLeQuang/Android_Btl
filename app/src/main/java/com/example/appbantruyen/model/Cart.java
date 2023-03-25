package com.example.appbantruyen.model;

public class Cart {
    private BookDetail bookDetail;
    private int amount;

    public BookDetail getMealDetail() {
        return bookDetail;
    }

    public void setMealDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
