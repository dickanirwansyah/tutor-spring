package com.dicka.springbootdemoajax.entity;

import java.io.Serializable;

public class Book implements Serializable{

    private int bookId;
    private String name;
    private int stock;
    private String author;

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
}
