package com.ndthang.quanlyquanan.models;

public class Drink {
    private String name;
    private String unitName;
    private int quantity;
    private double price;

    public Drink() {
        // Default constructor required for Firebase
    }
    public Drink(String name, String unitName, int quantity, double price){
        this.name = name;
        this.unitName = unitName;
        this.quantity = quantity;
        this.price = price;
    }
    public Drink(String name, String unitName) {
        this.name = name;
        this.unitName = unitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getUnitName() {
        return unitName;
    }
}
