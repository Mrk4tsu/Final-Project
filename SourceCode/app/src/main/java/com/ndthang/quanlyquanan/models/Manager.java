package com.ndthang.quanlyquanan.models;

public class Manager {
    String name;
    int quantity;
    String unit;

    public Manager() {
    }

    public Manager(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}
