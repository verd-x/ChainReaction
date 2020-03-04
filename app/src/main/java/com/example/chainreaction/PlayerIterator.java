package com.example.chainreaction;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class PlayerIterator implements Iterator<Player> {

    private int currPos = 0;
    private List<Player> list;

    public PlayerIterator(List<Player> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Player next() {
        currPos++;
        currPos %= list.size();
        return list.get(currPos);
    }

    @Override
    public void remove() {
        list.remove(currPos);
    }

    @Override
    public void forEachRemaining(@NonNull Consumer<? super Player> action) {

    }

    public Player getCurrent() {
        return list.get(currPos);
    }
}
