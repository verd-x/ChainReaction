package com.example.chainreaction;

public class Cell {
    private String playerId;
    private Position pos;
    private int fila, columna;
    private int numBolas;

    public Cell(int fila, int columna) {
        boolean bordeNorteSur = (fila == 0 || fila == Game.NFILAS);
        boolean bordeEsteOeste = (columna == 0 || columna == Game.NCOLUMNAS);
        if(bordeNorteSur && bordeEsteOeste) {
            pos = Position.CORNER;
        } else if(bordeEsteOeste || bordeNorteSur) {
            pos = Position.WALL;
        } else {
            pos = Position.CENTER;
        }

        this.fila = fila;
        this.columna = columna;
        numBolas = 0;
        playerId = null;
    }

    public void annadirBola(String playerId) throws Exception {
        if(this.playerId == null) {
            this.playerId = playerId;
        }
        if(this.playerId == playerId) {
            numBolas++;
        } else  {
            throw new Exception("La celda no es del jugador que la ha tocado.");
        }
    }

    public void comprobarExplosion() throws ExplosionException {
        int maxBolas = 4;
        switch(pos) {
            case CORNER:
               maxBolas = 2;
                break;
            case WALL:
                maxBolas = 3;
                break;
            case CENTER:
                maxBolas = 4;
                break;
        }
        if (numBolas >= maxBolas) {
            throw new ExplosionException();
        }
        numBolas = 0;
    }

    public int getNumBolas() {
        return numBolas;
    }

}
