package com.example.chainreaction;

import android.content.Context;
import android.widget.TextView;

public class PosTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int fila;
    private int columna;

    public PosTextView(Context context) {
        super(context);
    }

    public PosTextView(Context context, int fila, int columna) {
        super(context);
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
