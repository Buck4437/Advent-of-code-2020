package com.company.Day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Puzzle {

    private final int id;
    private final String puzzle;
    private int orientation = 0;
    /*
    0,  1, 2,  3, 4,  5, 6,  7
    U, FU, D, FD, L, FL, R, FR

        (R)
    U -> R
    FU -> L
    D -> FR
    FD -> FL
    R ->

     */

    public Puzzle(int id, String puzzle) {
        this.id = id;
        this.puzzle = puzzle;
    }

    @Override
    public String toString() {
        return "PuzzlePiece{" +
                ", id=" + id +
                ", puzzle=\n'" + puzzle + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public boolean hasEdge(String in) {
        ArrayList<String> edges = new ArrayList<>();
        ArrayList<String> tmp = getEdges();
        for (String tmp4 : tmp) {
            edges.add(tmp4);
            ArrayList<String> tmp5 = new ArrayList<>(Arrays.asList(tmp4.split("")));
            Collections.reverse(tmp5);
            edges.add(String.join("", tmp5));
        }

        for (String edge : edges) {
            if (in.equals(edge)) return true;
        }
        return false;
    }

    public ArrayList<String> getEdges() {
        ArrayList<String> edges = new ArrayList<>();
        edges.add(puzzle.split("\n")[0]);
        edges.add(puzzle.split("\n")[puzzle.split("\n").length - 1]);
        String tmp2 = "", tmp3 = "";
        for (String tmp4 : puzzle.split("\n")) {
            tmp2 += tmp4.split("")[0];
            tmp3 += tmp4.split("")[tmp4.split("").length - 1];
        }
        edges.add(tmp2);
        edges.add(tmp3);
        return edges;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
