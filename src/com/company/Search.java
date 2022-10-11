package com.company;

public abstract class Search {
    protected int numRuns = 0;
    protected long timetot = 0;
    protected int totNodes = 0;
    protected long goal = 5192138224l;
    protected long time;
    protected int pathLen;
    protected boolean print = true;

    abstract void search(Table t);

    public void clear() {
        int numRuns = 0;
        long timetot = 0;
        int totNodes = 0;
    }

    public void printCulStats(String name) {
        String s = name + ": \n Avg run time: " + timetot/numRuns + "\n Avg num Nodes: " + totNodes/numRuns;
        System.out.println(s);
    }

    public void Solve(Table t, int expectPathLen) {
        print = false;
        Solve(t);
        /*if(pathLen != expectPathLen) {
            System.out.println("ERROR: Exepected Path length: " + expectPathLen + ", But was: " + pathLen);
        }*/
    }

    public void Solve(Table t) {
        if(print) {
            System.out.print(this.toString() + ": ");
        }
        time = System.nanoTime();
        if(t.solvable()) {
            search(t);
        } else {
            System.out.println("\n" + t + "\nIs not Solvable\n");
        }
        timetot += (System.nanoTime() - time);
        numRuns++;
    }

    protected void printStats(int nodes, String p, Table t) {
        if(print) {
            String s = "\nTotal Nodes: " + nodes
                    + "\nTotal Time: " + (System.nanoTime() - time)/1000000
                    + " Ms\nPath length: " + p.length()
                    + "\nPath: " + p + "\n";
            System.out.println(s);
        }
        totNodes += nodes;
        pathLen = p.length();
    }
}
