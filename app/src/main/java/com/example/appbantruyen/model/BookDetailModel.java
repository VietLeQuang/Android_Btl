package com.example.appbantruyen.model;

import java.util.List;

public class BookDetailModel {
    private boolean success;
    private String message;
    private List<BookDetail> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookDetail> getResult() {
        return result;
    }

    public void setResult(List<BookDetail> result) {
        this.result = result;
    }
}
