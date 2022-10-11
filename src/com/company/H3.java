package com.company;

import java.util.function.Function;

public class H3 implements Function<Table, Double> {
    @Override
    public String toString() {
        return "h3";
    }

    @Override
    public Double apply(Table t) {
        TableIterator it = new TableIterator(t);
        double num = 0;
        int i = it.getIntNoZero(0) * it.getIntNoZero(1) * it.getIntNoZero(2);
        num += i == 24 ? 0 : 1;
        i = it.getIntNoZero(3) * it.getIntNoZero(4) * it.getIntNoZero(5);
        num += i == 210 ? 0 : 1;
        i = it.getIntNoZero(6) * it.getIntNoZero(7) * it.getIntNoZero(8);
        num += i == 72 ? 0 : 1;
        i = it.getIntNoZero(0) * it.getIntNoZero(3) * it.getIntNoZero(6);
        num += i == 80 ? 0 : 1;
        i = it.getIntNoZero(1) * it.getIntNoZero(4) * it.getIntNoZero(7);
        num += i == 162 ? 0 : 1;
        i = it.getIntNoZero(2) * it.getIntNoZero(5) * it.getIntNoZero(8);
        num += i == 21 ? 0 : 1;
        if(num < 6) {
            var tmp = i;
        }
        return num;
    }
}
