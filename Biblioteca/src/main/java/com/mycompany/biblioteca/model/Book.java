package com.mycompany.biblioteca.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int stock;
    

    public Book() {
    }

    public Book(String title, String author, String isbn, int stock, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.stock = stock;
     
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

  

    @Override
    public String toString() {
        return "book{" + "title=" + title + ", author=" + author + '}';
    }


    
}
