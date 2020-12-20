package com.company.Day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Part1 {

    static Hashtable<String, String> rules = new Hashtable<>();

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day20\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> ln = new ArrayList<>();

        while (sc.hasNextLine()) {
            ln.add(sc.nextLine());
        }

        String input = String.join("\n", ln);

        ArrayList<Puzzle> puzzles = new ArrayList<>();

        for (String puzzle : input.split("\n\n")) {
            int id = Integer.parseInt(puzzle.split("\n")[0].replaceAll("Tile |:", ""));
            String puzzlePicture = puzzle.replaceAll("Tile \\d+:\n", "");
            puzzles.add(new Puzzle(id,puzzlePicture));
        }

        ArrayList<Puzzle> corners = new ArrayList<>();
        for (Puzzle puzzle : puzzles) {
            int matches = 0;
            for (Puzzle puzzle2 : puzzles) {
                if (puzzle2.getId() == puzzle.getId()) continue;
                boolean hasMatch = false;
                for (String edge : puzzle.getEdges()) {
                    if (puzzle2.hasEdge(edge)) {
                        hasMatch = true;
                        break;
                    }
                }
                if (hasMatch) {
                    matches ++;
                }
                if (matches > 2) {
                    break;
                }
            }
            if (matches == 2) corners.add(puzzle);
        }

        System.out.println(corners);

        long mul = 1;

        for (Puzzle corner : corners) {
            mul *= corner.getId();
        }

        System.out.println(mul);
    }
}
