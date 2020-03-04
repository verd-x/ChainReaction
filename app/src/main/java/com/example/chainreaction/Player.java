package com.example.chainreaction;


import androidx.annotation.ColorInt;

public class Player {
    private String id;
    private String color;

    public Player(String color, String id) {
        this.id = id;
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }
}
