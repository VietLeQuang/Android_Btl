package com.example.appbantruyen.model;

public class Books {
    private int id;
    private String strBook, strBookThumb;
    private int idBook,idcategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrBook() {
        return strBook;
    }

    public void setStrBook(String strBook) {
        this.strBook = strBook;
    }

    public String getStrBookThumb() {
        return strBookThumb;
    }

    public void setStrBookThumb(String strBookThumb) {
        this.strBookThumb = strBookThumb;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }
}
