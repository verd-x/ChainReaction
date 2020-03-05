package com.example.chainreaction;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends AppCompatActivity {
    protected static int NFILAS = 9, NCOLUMNAS = 6;
    private Iterator<Player> currPlayerIt;
    private Player currPlayer;
    private Tablero tablero;
    private List<Player> players;
    private GridLayout layout;

    public Game(int NPLAYERS, int NFILAS, int NCOLUMNAS) {
        this.NFILAS = NFILAS;
        this.NCOLUMNAS = NCOLUMNAS;
        init(NPLAYERS);
    }

    public Game(int NPLAYERS) {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
        init(NPLAYERS);
    }

    public Game() {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
    }

    private void init(int nPlayers) {
        players = new ArrayList<>();
        for(int i = 0; i < nPlayers;i++) {
            players.add(new Player(PlayerColors.getColor(i), "p" + i));
        }
    }


    public String getCurrPlayer() {
        return currPlayer.getId();
    }

    public void nextTurn() {
        String winner = tablero.checkWinner();
        if(winner != null) {

        }
        currPlayer = currPlayerIt.next();
    }


}
