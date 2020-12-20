package com.company.Day20;

import java.util.ArrayList;

public class Puzzle {

    public final int NONE = -1;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public final int[] directions = {UP, DOWN, LEFT, RIGHT};

    private final int id;
    private String puzzle;
    private final String initState;

    public Puzzle(int id, String puzzle) {
        this.id = id;
        this.puzzle = puzzle;
        this.initState = puzzle;
    }

    public int getId() {
        return id;
    }

    public String getPuzzleNoBorder() {
        String[] rows = puzzle.split("\n");
        ArrayList<String> nRow = new ArrayList<>();
        for (int i = 1; i < rows.length - 1; i++) {
            nRow.add(rows[i].substring(1, rows[i].length() - 1));
        }
        return String.join("\n", nRow);
    }

    public int getOppositeDirection(int dir) {
        switch (dir) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return NONE;
        }
    }

    public Puzzle rotate() { //Clockwise
        ArrayList<String> nPuzzle = new ArrayList<>();
        for (int i = 0; i < puzzle.split("\n").length; i++) {
            String nRow = "";
            for (String row : puzzle.split("\n")) {
                nRow = row.split("")[i] + nRow;
            }
            nPuzzle.add(nRow);
        }
        puzzle = String.join("\n", nPuzzle);
        return this;
    }

    public Puzzle flip() { //Horizontally
        ArrayList<String> nPuzzle = new ArrayList<>();
        for (String row : puzzle.split("\n")) {
            String nRow = "";
            for (String s : row.split("")) {
                nRow = s + nRow;
            }
            nPuzzle.add(nRow);
        }
        puzzle = String.join("\n", nPuzzle);
        return this;
    }

    public Puzzle reset() {
        puzzle = initState;
        return this;
    }

    public String getEdge(int type) { //From top to bottom, Left to right
        switch (type) {
            case UP:
                return puzzle.split("\n")[0];
            case DOWN:
                return puzzle.split("\n")[puzzle.split("\n").length - 1];
            case LEFT:
                String left = "";
                for (String row : puzzle.split("\n")) {
                    left += row.split("")[0];
                }
                return left;
            case RIGHT:
                String right = "";
                for (String row : puzzle.split("\n")) {
                    right += row.split("")[row.split("").length - 1];
                }
                return right;
            default:
                return "";
        }
    }

    public boolean canFit(Puzzle puzzle2) {
        return canFitWhere(puzzle2) != NONE;
    }

    public int canFitWhere(Puzzle puzzle2) {
        if (id == puzzle2.id) return NONE;
        for (int direction : directions) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (getEdge(direction).equals(puzzle2.getEdge(getOppositeDirection(direction)))) {
                        return direction;
                    }
                    puzzle2.rotate();
                }
                puzzle2.flip();
            }
        }
        return NONE;
    }

    @Override
    public String toString() {
        return "PuzzlePiece{" +
                ", id=" + id +
                ", puzzle='\n" + puzzle + "\n'" +
                '}';
    }
}
