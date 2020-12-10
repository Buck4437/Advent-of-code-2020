package com.company.Question10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> n = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\com\\company\\Question10\\input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            n.add(Integer.parseInt(sc.nextLine()));
        }
        n.add(0);

        Collections.sort(n);
        n.add(n.get(n.size()-1)+3);

        int[] diffs = {0, 0, 0};
        for (int i = 0; i < n.size() - 1; i++){
            int diff = n.get(i+1) - n.get(i);
            diffs[diff - 1]++;
        }

        System.out.println("Part 1: " + diffs[0] * diffs[2]);

        long c = 1;
        int s = 0;
        for (int i = 0; i < n.size() - 1; i++){
            if (n.get(i+1) - n.get(i) == 3){
                c *= paths(n.subList(s, i + 1)); //multiplication law
                s = i + 1;
            }
        }

        System.out.println(c);
    }

    public static long paths(List<Integer> nodes){
        int size = nodes.size();
        if (size <= 2) return 1;
        long c = 0;
        for (int i = 1; i <= 3 && i <= size - 1; i++){
            if (nodes.get(i) - nodes.get(0) <= 3){
                c += paths(nodes.subList(i, size)); //addition law
            }
        }
        return c;
    }
}