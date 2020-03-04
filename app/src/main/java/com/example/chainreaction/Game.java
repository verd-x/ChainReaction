package com.example.chainreaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Game extends AppCompatActivity {
    protected static int NFILAS = 9, NCOLUMNAS = 6;
    private Tablero tablero;
    private List<Player> players;

    public Game(int NPLAYERS, int NFILAS, int NCOLUMNAS) {
        this.NFILAS = NFILAS;
        this.NCOLUMNAS = NCOLUMNAS;
    }
    public Game(int NPLAYERS) {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
    }

    public Game() {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayout layout = new GridLayout(this);
        layout.setRowCount(NFILAS);
        layout.setColumnCount(NCOLUMNAS);
        for(int i = 0; i < NFILAS;i++) {
            for(int j = 0; j <NCOLUMNAS; j++){
                layout.addView(createButton(layout));
            }

        }
        layout.setVisibility(View.VISIBLE);
        setContentView(R.layout.activity_game);
    }

    private Button createButton(GridLayout layout) {
        Button boton = new Button(this);
        boton.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        boton.setHeight(20);
        //boton.setText("Game");
        boton.setVisibility(View.VISIBLE);
        return boton;
    }



}
