package com.company.Day17;

import java.util.ArrayList;
import java.util.Hashtable;

public class Part2 {

    //iter 1: 29
    //tier 2: 60

    static String test = """
            .#.
            ..#
            ###""";

    static String input = """
            ..##.#.#
            ##....#.
            ....####
            #..##...
            #..#.##.
            .#..#...
            ##...##.
            .#...#..""";

    static int iter = 6;
    static int baseLength = input.split("\n").length;
    static int layerLength = baseLength + iter * 2;
    static int layerHeight = iter;
    static Hashtable<Integer, LayerCache> layers = new Hashtable<>();

    public static void main(String[] args) {

        String tmp = mul(emptyLayer() + "\n\n", iter);
        String tmp2 = tmp.substring(0, tmp.length() - 2);

        String base = tmp + expandedLayer() + "\n" + tmp2;

        layers.put(0, new LayerCache(base));

        for (int i = 1; i <= iter; i++) {
            layers.put(i, new LayerCache(emptyDimension()));
            layers.put(-i, new LayerCache(emptyDimension()));
            for (int w = -i; w <= i; w++) {
                System.out.printf("Iteration %s, w = %s\n", i, w);
                String newL = "";
                for (int z = -iter; z <= iter; z++) {
                    if (z < -i || z > i){
                        newL += emptyLayer() + "\n\n";
                        continue;
                    }

                    for (int y = 0; y < layerLength; y++) {
                        if (y < layerLength / 2 - baseLength - i || y > layerLength / 2 + baseLength + i){
                            newL += (mul(".", layerLength) + "\n");
                            continue;
                        }

                        for (int x = 0; x < layerLength; x++) {
                            if (x < layerLength / 2 - baseLength - i || x > layerLength / 2 + baseLength + i){
                                newL += ".";
                                continue;
                            }

                            int n = 0;
                            if (layers.containsKey(w - 1)) {
                                n += countCubes(w - 1, z, y, x, true);
                            }
                            if (layers.containsKey(w + 1)) {
                                n += countCubes(w + 1, z, y, x, true);
                            }
                            n += countCubes(w, z, y, x, false);
                            char symbol = layers.get(w).getData()
                                    .split("\n\n")[z + layerHeight]
                                    .split("\n")[y]
                                    .charAt(x);
                            if (symbol == '#'){
                                if (n == 2 || n == 3) newL += "#";
                                else newL += ".";
                            } else {
                                if (n == 3) newL += "#";
                                else newL += ".";
                            }
                        }
                        newL += "\n";
                    }
                    newL += "\n";
                }
                newL = newL.substring(0, newL.length() - 2);
                layers.get(w).setData(newL);
            }
            for (int w = -i; w <= i; w++) {
                layers.get(w).updateCache();
            }
        }

        int ans = 0;

        for (int w = -iter; w <= iter; w++) {
            String[] symbols = layers.get(w).getData()
                    .replaceAll("\n", "")
                    .replaceAll("\n\n", "")
                    .split("");
            for (String symbol : symbols) {
                if (symbol.equals("#")) ans++;
            }
        }

        System.out.println(ans);

    }

    public static int countCubes(int w, int z, int y, int x, boolean countItself) {
        int n = 0;
        String layer = layers.get(w).getData();

        ArrayList<int[]> dirs = getDirs(countItself);

        for (int[] offset : dirs) {
            int newX = offset[0] + x;
            int newY = offset[1] + y;
            int newZ = offset[2] + z;
            if (newX < 0 || newX >= layerLength
                    || newY < 0 || newY >= layerLength
                    || newZ < -layerHeight || newZ >= layerHeight) continue;
            if (layer.split("\n\n")[newZ + layerHeight]
                    .split("\n")[newY]
                    .charAt(newX) == '#'){
                n++;
            }
        }

        return n;
    }

    public static ArrayList<int[]> getDirs(boolean includeOrigin) {
        ArrayList<int[]> dirs = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i == 0 && j == 0 && k == 0 && !includeOrigin) continue;
                    dirs.add(new int[]{i, j, k});
                }
            }
        }
        return dirs;
    }

    public static String emptyDimension() {
        String str = mul(emptyLayer() + "\n\n", layerLength);
        return str.substring(0, str.length() - 1);
    }

    public static String emptyLayer() {
        String str = mul(mul(".", layerLength) + "\n", layerLength);
        return str.substring(0, str.length() - 1);
    }

    public static String expandedLayer() {
        String str = mul(mul(".", layerLength) + "\n", iter);
        for (String s : input.split("\n")) {
            String tmp = mul(".", iter);
            str += tmp + s + tmp + "\n";
        }
        str += mul(mul(".", layerLength) + "\n", iter);
        return str;
    }

    public static String mul(String s, int i) {
        String str = "";
        for (int j = 0; j < i; j++) {
            str += s;
        }
        return str;
    }
}
