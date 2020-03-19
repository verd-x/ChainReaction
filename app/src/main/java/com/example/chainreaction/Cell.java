package com.example.chainreaction;

class Cell {
    private String playerId;
    private Position pos;
    private int numBolas;
    private Game game;

    Cell(int fila, int columna, Game game) {
        boolean bordeNorteSur = (fila == 0 || fila == game.getFilas() - 1);
        boolean bordeEsteOeste = (columna == 0 || columna == game.getColumnas() - 1);
        if(bordeNorteSur && bordeEsteOeste) {
            pos = Position.CORNER;
        } else if(bordeEsteOeste || bordeNorteSur) {
            pos = Position.WALL;
        } else {
            pos = Position.CENTER;
        }

        this.game = game;
        numBolas = 0;
        playerId = null;
    }

    void annadirBola(String playerId, boolean sobreescribir) throws Exception {
        if(this.playerId == null) {
            this.playerId = playerId;
        } else if (!this.playerId.equals(playerId) && sobreescribir) {
            game.decreaseBalls(this.playerId, numBolas);
            game.addBalls(playerId, numBolas);
            this.playerId = playerId;

        }
        if(this.playerId.equals(playerId)) {
            numBolas++;
            game.addBall(playerId);
        } else{
            throw new Exception("La celda no es del jugador que la ha tocado.");
        }
        comprobarExplosion();
    }

    private void comprobarExplosion() throws ExplosionException {
        int maxBolas;
        switch(pos) {
            case CORNER:
               maxBolas = 2;
                break;
            case WALL:
                maxBolas = 3;
                break;
            case CENTER:
            default:
                maxBolas = 4;
                break;
        }
        if (numBolas >= maxBolas) {
            game.decreaseBalls(playerId, numBolas);
            numBolas = 0;
            this.playerId = null;
            throw new ExplosionException();
        }
    }

    int getNumBolas() {
        return numBolas;
    }

    String getPlayerId() {
        return playerId;
    }
}
