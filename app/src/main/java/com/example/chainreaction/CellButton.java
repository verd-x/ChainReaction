package com.example.chainreaction;

import android.content.Context;
import android.view.View;


public class CellButton extends androidx.appcompat.widget.AppCompatButton{

    private Cell cell;
    private Game game;

    public CellButton(Context context, Game game) {
        super(context);
        this.game = game;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }


    public void setOnClickListener() {
        super.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cell.annadirBola(game.getCurrPlayer(), false);
                    game.nextTurn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
