package com.example.chainreaction;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

class Tablero {

    private Map<Pair<Integer, Integer>, Cell> cellMap;
    private Game game;

    Tablero(Game game) {
        this.game = game;
        init();
    }

    private void reset() {
        init();
    } //TODO

    private void init() {
        cellMap = new HashMap<Pair<Integer, Integer>, Cell>();
        for (int fila = 0; fila < game.getFilas(); fila++) {
            for (int columna = 0; columna < game.getColumnas(); columna++) {
                add(fila, columna);
            }
        }
    }

    private void add(int fila, int columna) {
        Cell celda = new Cell(fila, columna, game);
        cellMap.put(new Pair<Integer, Integer>(fila, columna), celda);
    }

    boolean annadirBola(int fila, int columna, String playerId, boolean sobreescribir) throws Exception{
        boolean ret = true;
        if (existeCelda(fila, columna)) {
            Cell celda = cellMap.get(new Pair<Integer, Integer>(fila, columna));
            try {
                celda.annadirBola(playerId, sobreescribir);
            } catch (ExplosionException e) {
                ret = false;
                annadirBola(fila - 1, columna, playerId, true);
                annadirBola(fila, columna - 1, playerId, true);
                annadirBola(fila + 1, columna, playerId, true);
                annadirBola(fila, columna + 1, playerId, true);
            }
        } else
            ret = false;
        return ret;
    }

    private boolean existeCelda(int fila, int columna) {
        return (fila >= 0 && columna >= 0 && fila < game.getFilas() && columna < game.getColumnas());
    }

    Cell getCelda(int fila, int columna) {
        return cellMap.get(new Pair<Integer, Integer>(fila, columna));
    }
}
