package com.example.chainreaction;

import android.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tablero {

    private List<Cell> celdas;
    private Map<Pair<Integer,Integer>, Cell> cellMap;

    public Tablero() {
        init();
    }

    private void reset() {
        init();
    }

    private void init() {
        celdas = new LinkedList<Cell>();
        cellMap = new HashMap<Pair<Integer, Integer>, Cell>();
        for(int fila = 0; fila < Game.NFILAS; fila++) {
            for(int columna = 0; columna < Game.NCOLUMNAS; columna++) {
                add(fila, columna);
            }
        }
    }

    private void add(int fila, int columna) {
        Cell celda = new Cell(fila, columna);
        celdas.add(celda);
        cellMap.put(new Pair<Integer, Integer>(fila, columna), celda);
    }

    public void annadirBola(int fila, int columna, String playerId) throws Exception {
        cellMap.get(new Pair<Integer, Integer>(fila, columna)).annadirBola(playerId);
    }

}
