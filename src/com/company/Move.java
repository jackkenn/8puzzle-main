package com.company;

import java.util.HashMap;

public enum Move
{
    L {
        @Override
        Table move(Table t) {
            return t.left();
        }
    },
    R {
        @Override
        Table move(Table t) {
            return t.right();
        }
    },
    U {
        @Override
        Table move(Table t) {
            return t.up();
        }
    },
    D {
        @Override
        Table move(Table t) {
            return t.down();
        }
    },
    START {
        @Override
        Table move(Table t) {
            return null;
        }

        @Override
        public String toString() {
          return "";
        };
    };
    abstract Table move(Table t);
    private static HashMap<Long, Move[]> movelist = new HashMap<>() {{
        put(1l, new Move[] {D, R});
        put(1l << 4, new Move[] {D, L, R});
        put(1l << 8, new Move[] {D, L});
        put(1l << 12, new Move[] {U, D, R});
        put(1l << 16, new Move[] {U, D, L, R});
        put(1l << 20, new Move[] {U, D, L});
        put(1l << 24, new Move[] {U, R});
        put(1l << 28, new Move[] {U, L, R});
        put(1l << 32, new Move[] {U, L});
    }};
    public static Move[] getMoves(Table t) {
        return movelist.get(~t.getLong() & 4581298449l);
    }

    public static void printMoves(Table t) {
        String str = new String();
        for (Move move : getMoves(t)) {
            str += move + " ";
        }
        System.out.println(str);
    }
}

