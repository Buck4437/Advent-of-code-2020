package com.company.Day20;

import java.util.ArrayList;

public class Frame {

    public static final int UP = Puzzle2.UP;
    public static final int DOWN = Puzzle2.DOWN;
    public static final int LEFT = Puzzle2.LEFT;
    public static final int RIGHT = Puzzle2.RIGHT;

    private ArrayList<Puzzle2> puzzles;
    private int size;

    public Frame(ArrayList<Puzzle2> puzzles) {
        this.puzzles = puzzles;
        this.size = (int) Math.ceil(Math.sqrt(puzzles.size()));
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Puzzle2> solve() {
        ArrayList<Puzzle2> solved = new ArrayList<>();
        ArrayList<Puzzle2> unsolved = new ArrayList<>(puzzles);

        Puzzle2 base = locateCorner();
        unsolved.remove(base);

        boolean ready = false;
        int matches = 0;
        ArrayList<Integer> baseOrient = new ArrayList<>();
        for (Puzzle2 puzzle : puzzles) {
            if (base.canFit(puzzle)) {
                baseOrient.add(base.canFitWhere(puzzle));
            }
        }
        if (baseOrient.contains(RIGHT)) {
            if (!baseOrient.contains(DOWN)) {
                base.flip().rotate().rotate();
            }
        } else { //Contains Left
            if (baseOrient.contains(DOWN)) {
                base.flip();
            } else {
                base.rotate().rotate();
            }
        }
        solved.add(base);
        System.out.printf("%s/%s pieces solved... \n", solved.size(), size * size);

        for (int i = 0; i < size; i++) { //Row
            for (int j = 0; j < size; j++) { //Column
                if (i == 0 && j == 0) continue;
                for (Puzzle2 puzzle : unsolved) {
                    if (i != 0) { //Has top piece
                        Puzzle2 anchorTop = solved.get((i - 1) * size + j);
                        if (anchorTop.canFitWhere(puzzle) != DOWN) continue;
                        for (int k = 0; k < 2; k++) {
                            boolean ok = false;
                            for (int l = 0; l < 3; l++) {
                                if (anchorTop.getEdge(DOWN).equals(puzzle.getEdge(UP))) {
                                    ok = true;
                                    break;
                                } else {
                                    puzzle.rotate();
                                }
                            }
                            if (ok) break;
                            puzzle.flip();
                        }
                    }
                    if (j != 0) { //Has left piece
                        Puzzle2 anchorSide = solved.get(i * size + j - 1);
                        if (anchorSide.canFitWhere(puzzle) != RIGHT) continue;
                        if (i != 0) {
                            if (!anchorSide.getEdge(RIGHT).equals(puzzle.getEdge(LEFT))) continue;
                        } else {
                            for (int k = 0; k < 2; k++) {
                                boolean ok = false;
                                for (int l = 0; l < 3; l++) {
                                    if (anchorSide.getEdge(RIGHT).equals(puzzle.getEdge(LEFT))) {
                                        ok = true;
                                        break;
                                    } else {
                                        puzzle.rotate();
                                    }
                                }
                                if (ok) break;
                                puzzle.flip();
                            }
                        }
                    }
                    unsolved.remove(puzzle);
                    solved.add(puzzle);
                    System.out.printf("%s/%s pieces solved... \n", solved.size(), size * size);
                    break;
                }
            }

        }
        return solved;
    }

    public Puzzle2 locateCorner() { //Need one corner as anchor
        for (Puzzle2 base : puzzles) {
            int matches = 0;
            for (Puzzle2 puzzle : puzzles) {
                if (base.canFit(puzzle)) matches++;
                if (matches > 2) break;
            }
            if (matches == 2) return base;
        }
        return null;
    }

}
