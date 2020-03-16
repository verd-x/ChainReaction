package com.example.chainreaction;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends AppCompatActivity {
    protected static int NFILAS = 9, NCOLUMNAS = 6;
    private PlayerIterator currPlayerIt;
    private Player currPlayer;
    private Tablero tablero;
    private List<Player> players;
    private Map<String, Player> playersMap;
    private TableLayout layout;
    private Context current;

    public Game(int NPLAYERS, int NFILAS, int NCOLUMNAS) {
        this.NFILAS = NFILAS;
        this.NCOLUMNAS = NCOLUMNAS;
        init(NPLAYERS);
        tablero = new Tablero();
    }

    public Game(int nPlayers, Context current) {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
        init(nPlayers);
        tablero = new Tablero();
        this.current = current;
    }

    private void init(int nPlayers) {
        players = new ArrayList<>();
        playersMap = new HashMap<>();
        for(int i = 0; i < nPlayers;i++) {
            Player player = new Player( "j" + i);
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
        if(winner != null) {
            //TODO: poner toast
        }
        if(currPlayer.isDead()) {
            currPlayerIt.remove();
        }
        currPlayer = currPlayerIt.next();
    }

    public void start() {
        currPlayer = currPlayerIt.getCurrent();
    }

    public void cellClicked(int fila, int columna) {
        try {
            tablero.annadirBola(fila, columna, currPlayer.getId(), false);
            currPlayer.addCell();
            nextTurn();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumBolas(int fila, int columna) {
        int result = tablero.getCelda(fila,columna).getNumBolas();
        return result;
    }

    public Player getPlayer(int fila, int columna) {
        Player result = playersMap.get(tablero.getCelda(fila, columna).getPlayerId());
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
                Player cellPlayer = getPlayer(fila, col);
                if (cellPlayer != null) {
                    String cellPlayerId = cellPlayer.getId();
                    int colorPickerId = current.getResources().getIdentifier(cellPlayerId, "color", current.getPackageName());
                    if (colorPickerId != 0)
                        view.setBackground(new ColorDrawable(current.getResources().getColor(colorPickerId)));
                }
            }
        }
    }

    public static int getId(int a, int b) {
        return (((a+b)*(a+b+1))/2+b);
    }

    public void setLayout(TableLayout layout) {
        this.layout = layout;
    }

    public Player playerWon() {
        Player winner = null;
        if(players.size() == 1) {
            winner = currPlayer;
        }
        return winner;
    }
}
