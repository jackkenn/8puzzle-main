package com.company;

import java.util.function.Function;

public class H1 implements Function<Table, Double> {
    protected long goal = 5192138224l;

    @Override
    public String toString() {
        return "h1";
    }

    @Override
    public Double apply(Table t) {
        double num = 0;

        byte b = (byte) ((t.getLong()) ^ (goal) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 4) ^ (goal >> 4) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 8) ^ (goal >> 8) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 12) ^ (goal >> 12) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 16) ^ (goal >> 16) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 20) ^ (goal >> 20) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 24) ^ (goal >> 24) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 28) ^ (goal >> 28) & 15);
        num += b == 0 ? 0 : 1;
        b = (byte) ((t.getLong() >> 32) ^ (goal >> 32) & 15);
        num += b == 0 ? 0 : 1;

        return num;
    }
}

