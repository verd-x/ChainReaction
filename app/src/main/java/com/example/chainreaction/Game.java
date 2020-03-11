package com.example.chainreaction;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableLayout;

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
    private TableLayout layout;

    public Game(int NPLAYERS, int NFILAS, int NCOLUMNAS) {
        this.NFILAS = NFILAS;
        this.NCOLUMNAS = NCOLUMNAS;
        init(NPLAYERS);
        tablero = new Tablero();
    }

    public Game(int nPlayers) {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
        init(nPlayers);
        tablero = new Tablero();
    }

    private void init(int nPlayers) {
        players = new ArrayList<>();
        for(int i = 0; i < nPlayers;i++) {
            players.add(new Player(PlayerColors.getColor(i), "p" + i));
        }
        currPlayerIt = new PlayerIterator(players);
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

    public void start() {
        currPlayer = currPlayerIt.next();
    }

    public void cellClicked(int fila, int columna) {
        try {
            tablero.annadirBola(fila, columna, currPlayer.getId(), false);
            nextTurn();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumBolas(int fila, int columna) {
        int result = tablero.getCelda(fila,columna).getNumBolas();
        return result;
    }

    public static void setNFILAS(int NFILAS) {
        Game.NFILAS = NFILAS;
    }

    public static void setNCOLUMNAS(int NCOLUMNAS) {
        Game.NCOLUMNAS = NCOLUMNAS;
    }

    public void update() {
        for(int fila = 0; fila < Game.NFILAS;fila++) {
            for(int col = 0; col < Game.NCOLUMNAS; col++){
                int id = getId(fila, col);
                PosTextView view = layout.findViewById(id);
                String numBolas = Integer.toString(getNumBolas(fila, col));
                view.setText(numBolas);
            }
        }
    }

    public static int getId(int a, int b) {
        return (((a+b)*(a+b+1))/2+b);
    }

    public void setLayout(TableLayout layout) {
        this.layout = layout;
    }
}
