package com.example.chainreaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NumberPicker Filas-Columnas
        final NumberPicker npFilasXColumnas = findViewById(R.id.np_tamanno);
        final String[] values = {"3 x 2", "6 x 4", "9 x 6", "12 x 8"};
        npFilasXColumnas.setDisplayedValues(values);


        //NumberPicker Jugadores
        final NumberPicker npPlayers = findViewById(R.id.np_jugadorxs);
        npPlayers.setMaxValue(10);
        npPlayers.setMinValue(2);


        //StartButton
        Button button = (Button) findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                String tamanno = values[npFilasXColumnas.getValue()];
                int filas;
                int columnas;
                switch (tamanno) {
                    case "3 x 2":
                        filas = 2;
                        columnas = 2;
                        break;
                    case "6 x 4":
                        filas = 6;
                        columnas = 4;
                        break;
                    case "12 x 8":
                        filas = 12;
                        columnas = 8;
                        break;
                    default:
                    case "9 x 6":
                        filas = 9;
                        columnas = 6;
                        break;
                }
                i.putExtra("NFILAS", filas);
                i.putExtra("NCOLUMNAS", columnas);
                i.putExtra("NJUGADORES", npPlayers.getValue());
                startActivity(i);
            }
        });
    }


}
