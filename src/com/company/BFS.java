package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Search {
    private HashMap<Long, String> visited;
    private Queue<Table> queue;

    @Override
    public void search(Table t) {
        visited = new HashMap<>();
        queue = new LinkedList<>();

        if(!printGoal(t)) {
            for (Move m : Move.getMoves(t)) {
                Table n = m.move(t);
                queue.add(n);
                visited.putIfAbsent(n.getLong(), m.toString());
            }

            while (!queue.isEmpty() && t.getLong() != goal) {
                t = queue.poll();
                for (Move m : Move.getMoves(t)) {
                    Table n = m.move(t);
                    if(!visited.containsKey(n.getLong())) {
                        queue.add(n);
                        visited.put(n.getLong(), visited.get(t.getLong()) + m.toString());
                    }
                }
            }
        }

        printGoal(t);
    }

    private boolean printGoal(Table t) {
        if(t.getLong() == goal) {
            printStats(visited.size(), visited.get(t.getLong()), t);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BFS";
    }
}
