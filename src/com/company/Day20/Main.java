package com.company.Day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

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

        Frame frame = new Frame(puzzles);

        ArrayList<Puzzle> solvedPuzzles = frame.solve();
        System.out.println("Part 1: " +
                (long) solvedPuzzles.get(0).getId()
                * (long) solvedPuzzles.get(frame.getSize() - 1).getId()
                * (long) solvedPuzzles.get(solvedPuzzles.size() - 1).getId()
                * (long) solvedPuzzles.get(solvedPuzzles.size() - frame.getSize()).getId()); //Part 1!!!

        ArrayList<String> puzzlePics = new ArrayList<>();
        for (Puzzle puzzle : solvedPuzzles) {
            puzzlePics.add(puzzle.getPuzzleNoBorder());
        }


        int counter = 0;

        ArrayList<String> blocks = new ArrayList<>();
        ArrayList<String> blockRows = new ArrayList<>();

        while (puzzlePics.size() > 0) {
            blocks.add(puzzlePics.remove(0));
            counter++;
            if (counter == frame.getSize()) {
                counter = 0;
                ArrayList<String> merged = new ArrayList<>();
                for (int j = 0; j < blocks.get(0).split("\n").length; j++) {
                    String tmp = "";
                    for (String row : blocks) {
                        tmp += row.split("\n")[j];
                    }
                    merged.add(tmp);
                }
                blockRows.add(String.join("\n", merged));
                blocks = new ArrayList<>();
            }
        }
        String fullPic = String.join("\n", blockRows);

        ArrayList<int[]> smPos = new ArrayList<>();

        boolean orientOK = false;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (seaMonsterPos(fullPic).size() > 0) {
                    smPos = seaMonsterPos(fullPic);
                    orientOK = true;
                    break;
                }
                fullPic = rotate(fullPic);
            }
            if (orientOK) break;
            fullPic = flip(fullPic);
            if (i == 1) {
                System.out.println("you fucked up");
            }
        }

        String seaMonster = """
                                  #\s
                #    ##    ##    ###
                 #  #  #  #  #  #  \s""";

        ArrayList<int[]> smBodies = new ArrayList<>();

        for (int i = 0; i < seaMonster.split("\n").length; i++) {
            for (int j = 0; j < seaMonster.split("\n")[0].length(); j++) {
                if (seaMonster.split("\n")[i].charAt(j) == '#') {
                    smBodies.add(new int[]{i, j});
                }
            }
        }

        int picWidth = fullPic.split("\n")[0].length() + 1; //new line

        for (int[] pos : smPos) {
            for (int[] smBody : smBodies) {
                int row = pos[0] + smBody[0];
                int column = pos[1] + smBody[1];
                fullPic = setCharAt(row * picWidth + column, 'O', fullPic);
            }
        }

        int roughness = 0;

        for (char ch : fullPic.toCharArray()) {
            if (ch == '#') roughness ++;
        }

        System.out.println("Part 2: " + roughness);

    }

    public static String setCharAt(int index, char ch, String str) {
        return str.substring(0, index)
                + ch
                + str.substring(index + 1);
    }

    public static ArrayList<int[]> seaMonsterPos(String pic) {

        ArrayList<int[]> pos = new ArrayList<>();

        String sm = """
                                  #\s
                #    ##    ##    ###
                 #  #  #  #  #  #  \s""";

        String[] smRowRegex = sm.replaceAll(" ", ".").split("\n");
        int smLength = smRowRegex[0].length();

        String[] picRow = pic.split("\n");
        for (int i = 0; i < picRow.length - 2; i++) { //Row
            for (int j = 0; j <= picRow[i].length() - smLength; j++) { //char pos
                if (Pattern.compile(smRowRegex[0])
                        .matcher(picRow[i].substring(j, j+smLength)).find()) {
                    if (Pattern.compile(smRowRegex[1])
                            .matcher(picRow[i+1].substring(j, j+smLength)).find()) {
                        if (Pattern.compile(smRowRegex[2])
                                .matcher(picRow[i+2].substring(j, j+smLength)).find()) { //Yeah this is shitty but idc
                            pos.add(new int[]{i, j});
                        }
                    }
                }
            }
        }
        return pos;
    }


    public static String rotate(String str) {
        ArrayList<String> nStr = new ArrayList<>();
        for (int i = 0; i < str.split("\n").length; i++) {
            String nRow = "";
            for (String row : str.split("\n")) {
                nRow = row.split("")[i] + nRow;
            }
            nStr.add(nRow);
        }
        return String.join("\n", nStr);
    }

    public static String flip(String str) { //Horizontally
        ArrayList<String> nStr = new ArrayList<>();
        for (String row : str.split("\n")) {
            String nRow = "";
            for (String s : row.split("")) {
                nRow = s + nRow;
            }
            nStr.add(nRow);
        }
        return String.join("\n", nStr);
    }
}
