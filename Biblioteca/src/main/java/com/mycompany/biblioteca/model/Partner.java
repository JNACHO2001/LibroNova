package com.mycompany.biblioteca.model;

public class Partner {

    private int id;
    private String name;
    private boolean asset;

    public Partner() {
    }

    public Partner(int id, String name, boolean asset) {
        this.id = id;
        this.name = name;
        this.asset = asset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAsset() {
        return asset;
    }

    public void setAsset(boolean asset) {
        this.asset = asset;
    }

    @Override
    public String toString() {
        return "loan" + "name=" + name;
    }

}
