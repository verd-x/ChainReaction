package com.example.chainreaction;


import androidx.annotation.ColorInt;

public class Player {
    private String id;
    private int cells;
    private boolean init;

    public Player( String id) {
        this.id = id;
        this.cells = 0;
        this.init = false;
    }


    public String getId() {
        return id;
    }

    public void addCell() {
        if(cells++ == 0) {
            init = true;
        }
    }

    public boolean isDead() {
        return (cells == 0 && init);
    }

    public void decreaseBalls(int numBolas) {
        cells--;
    }

    public void addCells(int numBolas) {
        cells += numBolas;
    }
}
