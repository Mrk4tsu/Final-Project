package com.ndthang.quanlyquanan.models;

public class Unit {
    private String id; // Thêm trường id
    private String name;

    public Unit() {
        // Firebase requires an empty constructor
    }
    public Unit(String name) {
        this.name = name;
    }
    public Unit(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
