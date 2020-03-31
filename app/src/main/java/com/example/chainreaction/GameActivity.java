package com.example.chainreaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Integer cols = i.getIntExtra("columnas", 6);
        Integer filas = i.getIntExtra("filas", 9);
        Integer players = i.getIntExtra("NJUGADORES", 2);

        game = new Game(players, this, filas, cols);

        //LAYOUT
        TableLayout layout = new TableLayout(this);

        FrameLayout.LayoutParams params= new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        layout.setLayoutParams(params);
        layout.setStretchAllColumns(true);

        for(int fila = 0; fila < game.getFilas();fila++) {
            TableRow row = createRow();
            for(int col = 0; col < game.getColumnas(); col++){
                row.addView(new PosImageView(this, fila, col, game));
            }
            layout.addView(row);
        }

        layout.setVisibility(View.VISIBLE);
        game.setLayout(layout);
        game.start();
        game.update();
        setContentView(layout);


    }

    private TableRow createRow() {

        TableRow row = new TableRow(this);

        TableLayout.LayoutParams pRow = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        pRow.weight = 1;
        row.setLayoutParams(pRow);
        return row;
    }
}
