package com.example.chainreaction;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;

public class PosImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int fila;
    private int columna;


    public PosImageView(Context context, int fila, int columna, Game game) {
        super(context);
        this.fila = fila;
        this.columna = columna;
        this.setPadding(10,10,10,10);
        TableRow.LayoutParams param = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        );
        this.setLayoutParams(param);
        this.setOnClickListener(new CellOnClickListener(game));
        this.setId(Game.getId(fila, columna));
        this.setBackground(context.getResources().getDrawable(R.drawable.border));
        this.setVisibility(View.VISIBLE);
    }

    public PosImageView(Context context) {
        super(context);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
