package com.company.Day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day22\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> inputLine = new ArrayList<>();
        while (sc.hasNextLine()) {
            inputLine.add(sc.nextLine());
        }
        String input = String.join("\n", inputLine);

        String[] player1Str = input.split("\n\n")[0].replaceAll("Player 1:\n", "").split("\n");
        String[] player2Str = input.split("\n\n")[1].replaceAll("Player 2:\n", "").split("\n");

        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();

        for (String num : player1Str) {
            p1.add(Integer.parseInt(num));
        }
        for (String num : player2Str) {
            p2.add(Integer.parseInt(num));
        }

        while (p1.size() != 0 && p2.size() != 0) {
            int num1 = p1.remove(0), num2 = p2.remove(0);
            if (num1 > num2) {
                p1.add(num1);
                p1.add(num2);
            } else {
                p2.add(num2);
                p2.add(num1);
            }
        }

        ArrayList<Integer> winner = new ArrayList<>(p1.size() != 0 ? p1 : p2);
        int sum = 0;
        while (winner.size() > 0) {
            int size = winner.size();
            sum += size * winner.remove(0);
        }

        System.out.println(sum);

    }

}
