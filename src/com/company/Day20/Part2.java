package com.company.Day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Part2 {

    static Hashtable<String, String> rules = new Hashtable<>();

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day20\\test.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> ln = new ArrayList<>();

        while (sc.hasNextLine()) {
            ln.add(sc.nextLine());
        }

        String input = String.join("\n", ln);

        ArrayList<Puzzle2> puzzles = new ArrayList<>();

        for (String puzzle : input.split("\n\n")) {
            int id = Integer.parseInt(puzzle.split("\n")[0].replaceAll("Tile |:", ""));
            String puzzlePicture = puzzle.replaceAll("Tile \\d+:\n", "");
            puzzles.add(new Puzzle2(id,puzzlePicture));
        }

        Frame frame = new Frame(puzzles);



        
        ArrayList<Puzzle2> solvedPuzzles = frame.solve();
        System.out.println((long) solvedPuzzles.get(0).getId()
                * (long) solvedPuzzles.get(frame.getSize() - 1).getId()
                * (long) solvedPuzzles.get(solvedPuzzles.size() - 1).getId()
                * (long) solvedPuzzles.get(solvedPuzzles.size() - frame.getSize()).getId());
//        System.out.println(frame.solve());
    }
}
