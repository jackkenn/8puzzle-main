package com.company;

import java.util.Iterator;

public class TableIterator implements Iterator {
    private byte[] b = new byte[9];
    private int it = 8;

    TableIterator(Table t) {
        for (int i = 0; i < 9; i++) {
            b[i] |= (t.getLong() >> i * 4) & 15l;
        }
    }

    @Override
    public boolean hasNext() {
        return it > 0;
    }

    @Override
    public Object next() {
        it--;
        return b[it + 1];
    }

    public int getInt(int num) {
        return (b[8-num] & 1) == 0 ? 0 : (b[8 - num] >> 1) + 1;
    }

    public int getIntNoZero(int num) {
        return ((b[8-num] & 1) == 0 ? 0 : (b[8 - num] >> 1)) + 1;
    }

    public int nextInt() {
        it--;
        return (b[it + 1] & 1) == 0 ? 8 : (b[it + 1] >> 1);
    }

    public String nextStr() {
        it--;
        return (b[it + 1] & 1) == 0 ? "_" : String.valueOf((b[it + 1] >> 1) + 1);
    }
}
