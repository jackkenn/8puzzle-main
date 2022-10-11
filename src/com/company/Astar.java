package com.company;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;

public class Astar<T extends Function<Table, Double>> extends Search {
    private Queue<Vertex> open;
    private HashMap<Long, Vertex> graph;
    private T func;

    public Astar(T t) {
        func = t;
    }

    @Override
    public void search(Table t) {
        time = System.nanoTime();

        open = new PriorityQueue<>();
        graph = new HashMap<>();

        Vertex v = new Vertex(t);
        open.add(v);
        graph.put(v.cur.getLong(), v);

        while (!open.isEmpty() && open.peek().cur.getLong() != goal) {
            v = open.poll();
            for (Move m : Move.getMoves(v.cur)) {
                Table n = m.move(v.cur);
                double score = func.apply(n);
                Vertex tmp = v;
                Vertex u = graph.compute(n.getLong(), (k, e) -> (e == null) ? new Vertex(n, tmp.pre) : e);

                if(score + v.g + 1 < u.h + u.g) {
                    u.pre = v.pre + m;
                    u.g = v.g + 1;
                    u.h = score;
                    if(!open.contains(u)) {
                        open.add(u);
                    }
                }
            }
        }
        if(!open.isEmpty()) {
            printGoal(open.poll());
        }
    }

    private boolean printGoal(Vertex v) {
        if(v.cur.getLong() == goal) {
            printStats(graph.size(), v.pre, v.cur);
            return true;
        }
        return false;
    }

    private class Vertex implements Comparable<Vertex> {
        private double g;
        private double h;
        private final Table cur;
        private String pre;

        public Vertex(Table cur, String pre, Double g, double h) {
            this.cur = cur;
            this.pre = pre;
            this.g = g;
            this.h = h;
        }

        public Vertex(Table cur) {
            this(cur, new String(), 0d, 0d);
        }

        public Vertex(Table cur, String pre) {
            this(cur, pre, Double.MAX_VALUE, Double.MAX_VALUE);
        }

        @Override
        public int compareTo(Vertex o) {
            return (int) ((h + g) - (o.h + o.g));
        }

        @Override
        public boolean equals(Object obj) {
            if(obj != null && obj.getClass() == this.getClass()) {
                return ((Vertex) obj).cur.getLong() == cur.getLong();
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return func.toString();
    }
}
