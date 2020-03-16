package com.example.chainreaction;

import android.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tablero {

    private List<Cell> celdas;
    private Map<Pair<Integer, Integer>, Cell> cellMap;

    public Tablero() {
        init();
    }

    private void reset() {
        init();
    }

    private void init() {
        celdas = new LinkedList<Cell>();
        cellMap = new HashMap<Pair<Integer, Integer>, Cell>();
        for (int fila = 0; fila < Game.NFILAS; fila++) {
            for (int columna = 0; columna < Game.NCOLUMNAS; columna++) {
                add(fila, columna);
            }
        }
    }

    private void add(int fila, int columna) {
        Cell celda = new Cell(fila, columna);
        celdas.add(celda);
        cellMap.put(new Pair<Integer, Integer>(fila, columna), celda);
    }

    public void annadirBola(int fila, int columna, String playerId, boolean sobreescribir) throws Exception{

        if (existeCelda(fila, columna)) {
            Cell celda = cellMap.get(new Pair<Integer, Integer>(fila, columna));
            try {
                celda.annadirBola(playerId, sobreescribir);
            } catch (ExplosionException e) {
                annadirBola(fila - 1, columna, playerId, true);
                annadirBola(fila, columna - 1, playerId, true);
                annadirBola(fila + 1, columna, playerId, true);
                annadirBola(fila, columna + 1, playerId, true);
            }

        }
    }

    private boolean existeCelda(int fila, int columna) {
        return (fila >= 0 && columna >= 0 && fila < Game.NFILAS && columna < Game.NCOLUMNAS);
    }

    public Cell getCelda(int fila, int columna) {
        return cellMap.get(new Pair<Integer, Integer>(fila, columna));
    }


}
