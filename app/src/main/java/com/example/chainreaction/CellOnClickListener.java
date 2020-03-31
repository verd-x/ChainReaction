package com.example.chainreaction;

import android.view.View;

public class CellOnClickListener implements PosImageView.OnClickListener {

    Game game;


    public CellOnClickListener(Game game) {
        this.game = game;
    }

    @Override
    public void onClick(View view) {
        int fila = ((PosImageView) view).getFila();
        int columna = ((PosImageView) view).getColumna();
        game.cellClicked(fila, columna);
    }

}
