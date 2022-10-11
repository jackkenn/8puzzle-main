package com.company;

import java.util.Scanner;

public class Table {
    //private long table = 68108636944l;
    private long table;

    public Table() {
        table = 40014697399l;
    }

    public Table(String s) {
        Scanner scan = new Scanner(s);
        for (int i = 0; i < 9; i++) {
            int num = scan.nextInt();
            table = (table << 4) | (num == 0 ? 0 : ((num - 1) << 1) + 1);
        }
    }

    public Table(int num[]) {
        for (int i = 0; i < 9; i++) {
            table = (table << 4) | (num[i] == 0 ? 0 : ((num[i] - 1) << 1) + 1);
        }
    }

    public Table(long l) {
        table = l;
        //print();
    }

    public long getLong() {
        return table;
    }

    public Table right() {
        long mask = (~table) & 285282321l;
        mask = mask + (mask << 1) + (mask << 2) + (mask << 3);
        return new Table(((~(mask << 4)) & table) | (((mask << 4) & table) >> 4));
    }

    public Table left() {
        long mask = (~table) & 4564517136l;
        mask = mask + (mask << 1) + (mask << 2) + (mask << 3);
        return new Table(((~(mask >> 4)) & table) | (((mask >> 4) & table) << 4));
    }

    public Table down() {
        long mask = (~table) & 1118481l;
        mask = mask + (mask << 1) + (mask << 2) + (mask << 3);
        return new Table(((~(mask << 12)) & table) | (((mask << 12) & table) >> 12));
    }

    public Table up() {
        long mask = (~table) & 4581298176l;
        mask = mask + (mask << 1) + (mask << 2) + (mask << 3);
        return new Table(((~(mask >> 12)) & table) | (((mask >> 12) & table) << 12));
    }

    public boolean solvable() {
        int pairs = 0;
        TableIterator it = new TableIterator(this);
        for(int i = 0; i < 8; i++) {
            if(it.getInt(i) != 0) {
                for(int j = i + 1; j < 9; j++) {
                    if(it.getInt(i) > it.getInt(j) && it.getInt(j) != 0) {
                        pairs++;
                    }
                }
            }
        }
        return pairs%2 == 0;
    }

    public void print() {
        System.out.println(this + "\n");
    }

    @Override
    public String toString() {
        TableIterator it = new TableIterator(this);
        return it.nextStr() + " " + it.nextStr() + " " + it.nextStr() + "\n"
                + it.nextStr() + " " + it.nextStr() + " " + it.nextStr() + "\n"
                + it.nextStr() + " " + it.nextStr() + " " + it.nextStr();
    }
}
