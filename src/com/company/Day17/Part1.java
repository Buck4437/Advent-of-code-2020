package com.company.Day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {

        int iterations = 6;

        String input = """
                ..##.#.#
                ##....#.
                ....####
                #..##...
                #..#.##.
                .#..#...
                ##...##.
                .#...#..""";

        Hashtable<Integer, LayerCache> layers = new Hashtable<>();

        String base = input;
        int layerSize = base.split("\n").length + iterations * 2;

        for (int i = 1; i <= iterations; i++){
            base = expand(base);
            layers.put(i, new LayerCache(emptyLayer(layerSize)));
            layers.put(-i, new LayerCache(emptyLayer(layerSize)));
        }

        layers.put(0, new LayerCache(base));

        for (int i = 1; i <= iterations; i++) {
            System.out.println("Iteration " + i + ": \n");
            for (int z = -iterations; z <= iterations; z++) {
                String layer = layers.get(z).getData();
                String newLayer = "";
                for (int y = 0; y < layerSize; y++) {
                    for (int x = 0; x < layerSize; x++) {
                        int n = 0;
                        if (layers.containsKey(z - 1)) {
                            n += countCubes(layers.get(z-1).getData(), y, x, true);
                        }
                        if (layers.containsKey(z + 1)) {
                            n += countCubes(layers.get(z+1).getData(), y, x, true);
                        }
                        n += countCubes(layer, y, x, false);
                        if (layer.split("\n")[y].charAt(x) == '#'){
                            if (n == 2 || n == 3) {
                                newLayer += "#";
                            } else {
                                newLayer += ".";
                            }
                        } else {
                            if (n == 3) {
                                newLayer += "#";
                            } else {
                                newLayer += ".";
                            }
                        }
                    }
                    newLayer += "\n";
                }
                newLayer = newLayer.substring(0, newLayer.length() - 1);
                layers.get(z).setData(newLayer);
            }
            for (int z = -iterations; z <= iterations; z++) {
                LayerCache cache = layers.get(z);
                cache.updateCache();

                System.out.println("z-layer " + z + ": \n");
                System.out.println(cache.getData());
                System.out.println();
            }
        }

        int ans = 0;

        for (int z = -iterations; z <= iterations; z++) {
            String[] idk = layers.get(z).getData().replaceAll("\n", "").split("");
            for (String a : idk) {
                if (a.equals("#")) ans++;
            }
        }

        System.out.println(ans);

    }

    public static int countCubes(String layer, int y, int x, boolean countItself) {
        int layerSize = layer.split("\n").length;
        int n = 0;
        ArrayList<int[]> offsets = new ArrayList<>();
        offsets.add(new int[]{-1, -1});
        offsets.add(new int[]{-1, 0});
        offsets.add(new int[]{-1, 1});
        offsets.add(new int[]{0, -1});
        offsets.add(new int[]{0, 1});
        offsets.add(new int[]{1, -1});
        offsets.add(new int[]{1, 0});
        offsets.add(new int[]{1, 1});

        if (countItself) {
            offsets.add(new int[]{0, 0});
        }

        for (int[] offset : offsets) {
//            System.out.println(layer);
            int newX = offset[0] + x;
            int newY = offset[1] + y;
            if (newX < 0 || newX >= layerSize || newY < 0 || newY >= layerSize) continue;
            if (layer.split("\n")[newY].charAt(newX) == '#'){
                n++;
            }
        }

        return n;
    }

    public static String emptyLayer(int width) {
        String str = "";
        for (int i = 0; i < width; i++) {
            str += mul(".", width) + "\n";
        }
        return str.substring(0, str.length() - 1);
    }

    public static String mul(String s, int i) {
        String str = "";
        for (int j = 0; j < i; j++) {
            str += s;
        }
        return str;
    }

    public static String expand(String str) {
        String newStr = "";
        for (String s : str.split("\n")) {
            newStr += "." + s + ".\n";
        }
        int newSize = newStr.split("\n")[0].length();
        newStr = mul(".", newSize) + "\n" + newStr + mul(".", newSize);
        return newStr;
    }
}
