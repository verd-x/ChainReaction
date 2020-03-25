package com.example.chainreaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private int filas, columnas;
    private PlayerIterator currPlayerIt;
    private Player currPlayer;
    private Tablero tablero;
    private List<Player> players;
    private Map<String, Player> playersMap;

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    private TableLayout layout;
    private Context current;

    public Game(int nPlayers, Context current, int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        init(nPlayers);
        tablero = new Tablero(this);
        this.current = current;
    }

    private void init(int nPlayers) {
        players = new ArrayList<>();
        playersMap = new HashMap<>();
        for (int i = 0; i < nPlayers; i++) {
            Player player = new Player("j" + i);
            players.add(player);
            playersMap.put(player.getId(), player);
        }
        currPlayerIt = new PlayerIterator(players);
    }


    public String getCurrPlayer() {
        return currPlayer.getId();
    }

    public void nextTurn() {
        Player winner = playerWon();
        if (winner != null) {
           showPopup();
        } else
            currPlayer = currPlayerIt.next();
    }

    public void start() {
        currPlayer = currPlayerIt.getCurrent();
    }

    public void cellClicked(int fila, int columna) {
        try {
            tablero.annadirBola(fila, columna, currPlayer.getId(), false);
            update();
            nextTurn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumBolas(int fila, int columna) {
        return tablero.getCelda(fila, columna).getNumBolas();
    }

    public Player getPlayer(int fila, int columna) {
        return playersMap.get(tablero.getCelda(fila, columna).getPlayerId());
    }

    public void update() {
        int i = 0;
        while (i < players.size()) {
            if (players.get(i).isDead()) {
                playersMap.remove(players.get(i).getId());
                players.remove(i);
            } else {
                i++;
            }
        }

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int id = getId(fila, col);
                PosTextView view = layout.findViewById(id);
                String numBolas = Integer.toString(getNumBolas(fila, col));
                view.setText(numBolas);
                Player cellPlayer = getPlayer(fila, col);
                if (cellPlayer != null) {
                    String cellPlayerId = cellPlayer.getId();
                    int colorPickerId = current.getResources().getIdentifier(cellPlayerId, "color", current.getPackageName());
                    if (colorPickerId != 0) {}
                        view.setBackground(new ColorDrawable(current.getResources().getColor(colorPickerId)));
                } else {
                    view.setBackground(new ColorDrawable(current.getResources().getColor(R.color.colorPrimary)));
                }
            }
        }
    }

    public static int getId(int a, int b) {
        return (((a + b) * (a + b + 1)) / 2 + b);
    }

    public void setLayout(TableLayout layout) {
        this.layout = layout;
    }

    public Player playerWon() {
        Player winner = null;
        if (players.size() == 1) {
            winner = currPlayer;
        }
        return winner;
    }

    public void decreaseBalls(String playerId, int numBolas) {
        playersMap.get(playerId).decreaseBalls(numBolas);
    }

    public void addBall(String playerId) {
        playersMap.get(playerId).addCell();
    }

    public void addBalls(String playerId, int numBolas) {
        playersMap.get(playerId).addCells(numBolas);
    }

    private void showPopup() {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                current.getSystemService(current.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_player_won, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(current, MainActivity.class);
                current.startActivity(i);
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
