package com.company;

import java.util.HashMap;
import java.util.HashSet;

public class IDS extends Search {
    private static int _nodes;
    private HashMap<Long, String> Paths = new HashMap<>();
    private HashSet<Long> visited = new HashSet<>();

    private Table IDS(Table t, int depth) {
        if(t.getLong() == goal || depth < 1) {
            return t;
        } else {
            for (Move m : Move.getMoves(t)) {
                Table n = m.move(t);
                if(!visited.contains((n.getLong() << 16) | depth)) {
                    visited.add((n.getLong() << 16) | depth);
                    String s = Paths.get(t.getLong()) + m.toString();
                    Paths.compute(n.getLong(), (k, v) -> (v==null || s.length() < v.length()) ? s : v);
                    Table tmp = IDS(n, depth - 1);
                    if(tmp.getLong() == goal) {
                        return tmp;
                    }
                }
            }
        }
        return t;
    }

    public void search(Table t, int maxDepth) {
        time = System.nanoTime();
        _nodes = 0;

        if(printGoal(t))
            return;

        for (Move m : Move.getMoves(t)) {
            Paths.put(m.move(t).getLong(), m.toString());
        }

        for(int i = 0; i < maxDepth; i++) {
            for (Move m : Move.getMoves(t)) {
                _nodes += visited.size();
                visited = new HashSet<>();
                Table n = m.move(t);
                visited.add(n.getLong() << 16);
                Table tmp = IDS(n, i);
                if(printGoal(tmp)) {
                    return;
                }
            }
        }
    }

    private boolean printGoal(Table t) {
        if(t.getLong() == goal) {
            printStats(_nodes, Paths.get(t.getLong()), t);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "IDS";
    }

    @Override
    void search(Table t) {
        search(t, 100);
    }
}
