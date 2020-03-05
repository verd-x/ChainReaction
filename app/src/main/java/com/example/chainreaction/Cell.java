package com.example.chainreaction;

public class Cell {
    private String playerId;
    private Position pos;
    private int fila, columna;
    private int numBolas;

    public Cell(int fila, int columna) {
        boolean bordeNorteSur = (fila == 0 || fila == GameActivity.NFILAS - 1);
        boolean bordeEsteOeste = (columna == 0 || columna == GameActivity.NCOLUMNAS - 1);
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

    public void annadirBola(String playerId, boolean sobreescribir) throws Exception {
        if(this.playerId == null || sobreescribir) {
            this.playerId = playerId;
        }
        if(this.playerId == playerId && !sobreescribir) {
            numBolas++;
        } else  {
            throw new Exception("La celda no es del jugador que la ha tocado.");
        }
        comprobarExplosion();
    }

    private void comprobarExplosion() throws ExplosionException {
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
            numBolas = 0;
            throw new ExplosionException();
        }
    }

    public int getNumBolas() {
        return numBolas;
    }

    public String getPlayerId() {
        return playerId;
    }
}
