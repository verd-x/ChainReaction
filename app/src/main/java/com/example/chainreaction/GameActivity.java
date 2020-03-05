package com.example.chainreaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    protected static int NFILAS = 9, NCOLUMNAS = 6;
    private Tablero tablero;
    private List<Player> players;

    public GameActivity(int NPLAYERS, int NFILAS, int NCOLUMNAS) {
        this.NFILAS = NFILAS;
        this.NCOLUMNAS = NCOLUMNAS;

    }
    public GameActivity(int NPLAYERS) {
        this.NFILAS = 9;
        this.NCOLUMNAS = 6;
    }

    public GameActivity() {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TableLayout layout = new TableLayout(this);

        FrameLayout.LayoutParams params= new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        layout.setLayoutParams(params);
        layout.setStretchAllColumns(true);

        for(int i = 0; i < NFILAS;i++) {
            TableRow row = createRow();
            for(int j = 0; j < NCOLUMNAS; j++){
                row.addView(createTextView());
            }
            layout.addView(row);
        }

        layout.setVisibility(View.VISIBLE);
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

    private TextView createTextView() {
        TextView view = new TextView(this);
        view.setText("Hello");
        view.setGravity(Gravity.CENTER);
        TableRow.LayoutParams param = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                 1.0f
        );
        view.setLayoutParams(param);

        view.setVisibility(View.VISIBLE);
        return view;
    }



}
