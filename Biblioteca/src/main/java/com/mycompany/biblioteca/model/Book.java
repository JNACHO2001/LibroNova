package com.mycompany.biblioteca.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int stock;
    private boolean available;

    public Book() {
    }

    public Book(String title, String author, String isbn, int stock, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.stock = stock;
        this.available = available;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "book{" + "title=" + title + ", author=" + author + '}';
    }


    
}
