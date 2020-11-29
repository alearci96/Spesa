package com.example.spesa;

public class Food_part {
    int id;
    private String name;
    private String qty;

    public Food_part(int id, String name, String qty) {
        this.id = id;
        this.name = name;
        this.qty = qty;
    }

    public Food_part(String name, String qty) {
        this.name = name;
        this.qty = qty;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
