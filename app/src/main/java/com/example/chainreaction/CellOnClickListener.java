package com.example.chainreaction;

import android.view.View;

public class CellOnClickListener implements PosTextView.OnClickListener {

    Game game;


    public CellOnClickListener(Game game) {
        this.game = game;
    }

    @Override
    public void onClick(View view) {
        int fila = ((PosTextView) view).getFila();
        int columna = ((PosTextView) view).getColumna();
        game.cellClicked(fila, columna);
    }

}
