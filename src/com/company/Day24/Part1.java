package com.company.Day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day24\\input.txt");
        Scanner sc = new Scanner(file);

        //Not in arrayList = white, in = black

        ArrayList <int[]> tiles = new ArrayList<>();

        while (sc.hasNextLine()) {
            int[] c = toCoor(sc.nextLine());
            ArrayList<Integer> test = new ArrayList<>();
            for (int a : c) {
                test.add(a);
            }
            System.out.println(test);
            boolean equals = false;
            for (int[] tile : tiles) {
                if (Arrays.equals(tile, c)) {
                    equals = true;
                    tiles.remove(tile);
                    break;
                }
            }
            if (!equals) {
                tiles.add(c);
            }
        }

        System.out.println(tiles.size());

    }

    public static int[] toCoor(String path) {

        int[] coordinates = {0, 0};

        //x: ne, sw  y: nw, se       e,w => (x + 1,y - 1), (x- 1, y+1)

        while (path.length() > 0) {
            char c = path.charAt(0);
            char c2 = ' ';
            if (path.length() > 1) {
                c2 = path.charAt(1);
            }
            switch (c) {
                case 'e':
                    coordinates[0]++;
                    coordinates[1]--;
                    path = path.substring(1);
                    break;
                case 'w':
                    coordinates[0]--;
                    coordinates[1]++;
                    path = path.substring(1);
                    break;
                case 'n':
                    if (c2 == 'e') {
                        coordinates[0]++;
                    } else {
                        coordinates[1]++;
                    }
                    path = path.substring(2);
                    break;
                case 's':
                    c2 = path.charAt(1);
                    if (c2 == 'e') {
                        coordinates[1]--;
                    } else {
                        coordinates[0]--;
                    }
                    path = path.substring(2);
                    break;
                default:
                    path = path.substring(1); //skip char
            }
        }

        return coordinates;

    }

}
