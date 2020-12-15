package com.company.Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> treeLayout = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day03\\input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            treeLayout.add(sc.nextLine());
        }

        System.out.printf("1,1: %s\n3,1: %s\n5,1: %s\n7,1: %s\n1,2: %s\nTotal: %s",
                countTrees(1,1),
                countTrees(3,1),
                countTrees(5,1),
                countTrees(7,1),
                countTrees(1,2),
                countTrees(1,1) * countTrees(3,1) *  countTrees(5,1) *  countTrees(7,1) *  countTrees(1,2)
        );

    }

    public static long countTrees(int right, int down){
        int column = 0;
        long trees = 0;

        for (int row = 0; row < treeLayout.size(); row += down){
            String treeRow = treeLayout.get(row);
            if (treeRow.charAt(column) == '#'){
                trees++;
            }
            column = (column + right) % treeLayout.get(0).length();
        }

        return trees;
    }
}
