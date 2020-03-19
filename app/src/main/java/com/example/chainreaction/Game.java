package com.example.chainreaction;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.view.ViewGroup.LayoutParams;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends AppCompatActivity {
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
            LayoutInflater inflater = (LayoutInflater) current.getSystemService(LAYOUT_INFLATER_SERVICE);

            // Inflate the custom layout/view
            View customView = inflater.inflate(R.layout.popup_player_won, null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
            // Initialize a new instance of popup window
            final PopupWindow mPopupWindow = new PopupWindow(
                    customView,
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );

            // Set an elevation value for popup window
            // Call requires API level 21
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }

            // Get a reference for the custom view close button
            ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

            // Set a click listener for the popup window close button
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the popup window
                    mPopupWindow.dismiss();
                }
            });
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
                    if (colorPickerId != 0)
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
}
