package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length == 1) {
            compare(args);
        } else {
            solve(args);
        }
    }

    private static void compare(String[] args) {
        List<Search> l = new ArrayList<>();
        l.add(new Astar(new H1()));
        l.add(new Astar(new H2()));
        l.add(new Astar(new H3()));
        l.add(new BFS());
        l.add(new IDS());
        String[] L8 = new String[] { "152.txt", "154.txt", "158.txt", "160.txt", "164.txt", "193.txt", "195.txt"
                , "198.txt", "208.txt", "210.txt", "224.txt", "225.txt", "234.txt", "236.txt", "238.txt", "239.txt"
                , "250.txt", "253.txt", "256.txt", "258.txt" };

        int[] L15 = new int[] { 4767, 4768, 4771, 4773, 4775, 4778, 4780, 4784, 4789, 4793, 4798, 4800
                , 5108, 5109, 5115, 5120, 5123, 5126, 5127, 5130 };

        int[] L24 = new int[] { 131248, 131256, 131264, 131271, 131274, 131279, 131304, 131311, 131324
                , 131345, 131370, 131374, 131378, 131394, 131400, 131406, 131425, 131449, 131455 };

        for (String s : L8) {
            Table t = buildTable(args[0] + "\\L8\\" + s);
            l.forEach(x -> {
                if(t.solvable())
                    x.Solve(t, 8);
            });
        }
        l.forEach(x -> {
            x.printCulStats(x.toString());
            x.clear();
        });

        for (int i : L15) {
            Table t = buildTable(args[0] + "\\L15\\" + i + ".txt");
            l.forEach(x -> {
                if(t.solvable())
                    x.Solve(t, 15);
            });
        }
        l.forEach(x -> {
            x.printCulStats(x.toString());
            x.clear();
        });

        for (int i : L24) {
            Table t = buildTable(args[0] + "\\L24\\" + i + ".txt");
            l.forEach(x -> {
                if(t.solvable())
                    x.Solve(t, 24);
            });
        }
        l.forEach(x -> {
            x.printCulStats(x.toString());
            x.clear();
        });

    }

    private static Table buildTable(String s) {
        try {
            File file = new File(s);
            Scanner scan = new Scanner(file);
            int arr[] = new int[9];
            for(int i = 0; i < 9; i ++) {
                if(scan.hasNextInt()) {
                    arr[i] = scan.nextInt();
                } else {
                    scan.next();
                    arr[i] = 0;
                }
            }
            return new Table(arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void solve(String[] args) {
        Table t = buildTable(args[0]);
        Astar h1 = new Astar(new H1());
        Astar h2 = new Astar(new H2());
        Astar h3 = new Astar(new H3());
        BFS bfs = new BFS();
        IDS ids = new IDS();

        switch (args.length >= 2 ? args[1] : "") {
            case "BFS":
                bfs.Solve(t);
                break;

            case "IDS":
                ids.Solve(t);
                break;

            case "h1":
                h1.Solve(t);
                break;

            case "h2":
                h2.Solve(t);
                break;

            case "h3":
                h3.Solve(t);
                break;

            default:
                bfs.Solve(t);
                ids.Solve(t);
                h1.Solve(t);
                h2.Solve(t);
                h3.Solve(t);
        }
    }
}
