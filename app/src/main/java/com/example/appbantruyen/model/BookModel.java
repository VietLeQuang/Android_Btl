package com.example.appbantruyen.model;

import java.util.List;

public class BookModel {
    private boolean success;
    private String message;
    private List<Books> result;

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

    public List<Books> getResult() {
        return result;
    }

    public void setResult(List<Books> result) {
        this.result = result;
    }
}
