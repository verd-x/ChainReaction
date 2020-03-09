package com.example.chainreaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NumberPicker Filas
        final NumberPicker npFilas = findViewById(R.id.np_filas);
        npFilas.setMaxValue(15);
        npFilas.setMinValue(2);

        //NumberPicker Columnas
        final NumberPicker npColumnas = findViewById(R.id.np_columnas);
        npColumnas.setMaxValue(10);
        npColumnas.setMinValue(2);

        //NumberPicker Jugadores
        final NumberPicker npPlayers = findViewById(R.id.np_players);
        npPlayers.setMaxValue(10);
        npPlayers.setMinValue(2);


        //StartButton
        Button button = (Button) findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                i.putExtra("NFILAS", npFilas.getValue());
                i.putExtra("NCOLUMNAS", npColumnas.getValue());
                i.putExtra("NJUGADORES", npPlayers.getValue());
                startActivity(i);
            }
        });
    }


}
