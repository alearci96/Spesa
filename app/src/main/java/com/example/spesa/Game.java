package com.example.spesa;

public class Game {
    int id;
    private String name;
    private String mark;

    public Game(int id, String name, String mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Game(String name, String mark) {
        this.name = name;
        this.mark = mark;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String qty) {
        this.mark = qty;
    }
}