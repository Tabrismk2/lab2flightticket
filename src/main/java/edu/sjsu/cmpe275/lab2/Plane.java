package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;

@Embeddable// This tells Hibernate to make a table out of this class

public class Plane {

    private int capacity;

    private String model;

    private String manufacturer;

    private int year;


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}