package com.company.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\com\\company\\Day11\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> inLine = new ArrayList<>();

        while (sc.hasNextLine()){
            inLine.add(sc.nextLine());
        }

        //part 2 only, too lazy zzzzzzzzzzzzz

        String plan = String.join("\n", inLine);

        while (true){

            String[][] planAry = text2DArray(plan);

            StringBuilder out = new StringBuilder();
            for (int i = 0; i < planAry.length; i++) {
                for (int j = 0; j < planAry[i].length; j++) {
                    String ogSym = planAry[i][j];
                    if (ogSym.equals(".")) {
                        out.append(".");
                        continue;
                    }
                    int ocp = 0;
                    int[][] offBs = {{-1, -1}, {-1, 0}, {-1, 1},
                                     {0, -1},           {0, 1},
                                     {1, -1},  {1, 0},  {1, 1}};

                    for (int k = 0; k <= 7; k++) {
                        int[] offB = offBs[k];
                        int mul = 1;
                        int[] coor = {offB[0] * mul + i, offB[1] * mul + j};
                        while ((coor[0] >= 0) && (coor[0] < planAry.length)
                                && (coor[1] >= 0) && (coor[1] < planAry[i].length)) {
                            boolean es = true;
                            switch (planAry[coor[0]][coor[1]]) {
                                case "L" -> {}
                                case "#" -> ocp++;
                                default -> es = false;
                            }
                            if (es) break;
                            mul ++;
                        }
                    }
                    if (ocp >= 5) out.append("L");
                    else if (ocp == 0) out.append("#");
                    else out = new StringBuilder(out.append(ogSym));
                }

                out.append("\n");
            }

            out = new StringBuilder(out.substring(0, out.length() - 1));

            if (plan.equals(out.toString())) break;
            plan = out.toString();
        }

        int ocp = 0;

        for (char c : plan.toCharArray()){{
            if (c == '#') ocp++;
        }}

        System.out.println(ocp);

    }

    public static String[][] text2DArray(String input){
        String[] rows = input.split("\n");
        String[][] out = new String[rows.length][];
        for (int i = 0; i < rows.length; i++){
            out[i] = rows[i].split("");
        }

        return out;
    }
}
