package com.company.Day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day24\\input.txt");
        Scanner sc = new Scanner(file);

        //Not in arrayList = white, in = black

        ArrayList <String> tiles = new ArrayList<>();

        while (sc.hasNextLine()) {
            int[] c = toCoor(sc.nextLine());
            String str = toStr(c);
            if (tiles.contains(str)) {
                tiles.remove(str);
            } else {
                tiles.add(str);
            }
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("Day " + (i + 1));
            Hashtable<String, Integer> nbCount = new Hashtable<>();
            for (String tile : tiles) {
                int x = Integer.parseInt(tile.split(",")[0]);
                int y = Integer.parseInt(tile.split(",")[1]);
                int[][] neighbours = {
                        {x + 1, y},
                        {x - 1, y},
                        {x, y + 1},
                        {x, y - 1},
                        {x + 1, y - 1},
                        {x - 1, y + 1},
                };
                for (int[] nb : neighbours) {
                    String str = toStr(nb);
//                    System.out.println(str);
                    if (nbCount.containsKey(str)) {
                        nbCount.put(str, nbCount.get(str) + 1);
                    } else {
                        nbCount.put(str, 1);
                    }
                }

                if (!nbCount.containsKey(tile)) {
                    nbCount.put(tile, 0);
                }
            }
            Enumeration<String> keys = nbCount.keys();

            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                int count = nbCount.get(key);
                if (tiles.contains(key)) { //black: 0, 2+
                    if (count == 0 || count > 2) {
                        tiles.remove(key);
                    }
                } else { //white: 2
                    if (count == 2) {
                        tiles.add(key);
                    }
                }
            }

            System.out.println(tiles.size());
        }

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

    public static String toStr(int[] str) {
        return str[0] + "," + str[1];
    }

}
