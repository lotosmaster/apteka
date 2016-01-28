package com.example.xd.lab;


import java.io.Serializable;

public class Medicine implements Serializable {

    //private variables
    private int _id;
    private String name;
    private String country;
    private String shelfLife;
    private String internationalName;
    private String category;
    private String status;

    // Empty constructor
    public Medicine() {

    }

    // constructor
    public Medicine(int id, String name, String srok, String country, String names, String category) {
        this._id = id;
        this.name = name;
        this.country = country;
        this.shelfLife = srok;
        this.internationalName = names;
        this.category = category;
    }

    public Medicine(String name, String srok, String country, String names, String category) {
        this.name = name;
        this.country = country;
        this.shelfLife = srok;
        this.internationalName = names;
        this.category = category;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this.name;
    }

    // setting name
    public void setName(String name) {
        this.name = name;
    }

    // getting srok
    public String getShelfLife() {
        return this.shelfLife;
    }

    // setting srok
    public void setShelLife(String srok) {
        this.shelfLife = srok;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInternationalName() {
        return this.internationalName;
    }

    public void setInternationalName(String names) {
        this.internationalName = names;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


