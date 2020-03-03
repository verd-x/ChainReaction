package com.example.chainreaction;

import java.util.List;

public class Game {
    protected static int NFILAS, NCOLUMNAS;
    private Tablero tablero;
    private List<Player> players;

    public Game(int NPLAYERS, int NFILAS, int NCOLUMNAS) {
        this.NFILAS = NFILAS;
        this.NCOLUMNAS = NCOLUMNAS;
    }
    public Game(int NPLAYERS) {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
    }



}
