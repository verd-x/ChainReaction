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
        Integer cols = i.getIntExtra("NCOLUMNAS", 6);
        Integer filas = i.getIntExtra("NFILAS", 9);
        Integer players = i.getIntExtra("NPLAYERS", 2);

        game = new Game(players, this);
        Game.setNFILAS(filas);
        Game.setNCOLUMNAS(cols);

        //LAYOUT
        TableLayout layout = new TableLayout(this);

        FrameLayout.LayoutParams params= new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        layout.setLayoutParams(params);
        layout.setStretchAllColumns(true);

        for(int fila = 0; fila < Game.NFILAS;fila++) {
            TableRow row = createRow();
            for(int col = 0; col < Game.NCOLUMNAS; col++){
                row.addView(createTextView(fila, col));
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

    private PosTextView createTextView(int fila, int columna) {
        PosTextView view = new PosTextView(this, fila, columna);
        view.setText("Hello");
        view.setGravity(Gravity.CENTER);
        TableRow.LayoutParams param = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                 1.0f
        );
        view.setLayoutParams(param);
        view.setOnClickListener(new CellOnClickListener(game));
        view.setId(Game.getId(fila, columna));


        view.setVisibility(View.VISIBLE);
        return view;
    }


}
