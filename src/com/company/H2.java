package com.company;

import java.util.function.Function;

public class H2 implements Function<Table, Double> {
    @Override
    public String toString() {
        return "h2";
    }

    @Override
    public Double apply(Table t) {
        TableIterator it = new TableIterator(t);
        double num = 0;
        for (int i = 0; i < 9; i++) {
            int tile = it.nextInt();
            num += Math.abs((tile % 3 - i % 3)) + Math.abs(((tile / 3) % 3 - (i / 3) % 3));
        }

        return num;
    }
}
