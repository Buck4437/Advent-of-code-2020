package com.company.Day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class Part2 {

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

        System.out.println(game(p1, p2, true));

    }

    public static int game(ArrayList<Integer> p1, ArrayList<Integer> p2, boolean countScore) {

        // 1 if player 1 wins, 2 if player 2 wins, countScore makes function returns the winner score instead;
        Hashtable<ArrayList<Integer>, ArrayList<Integer>> previousStates = new Hashtable<>();

        while (p1.size() != 0 && p2.size() != 0) {
            if (previousStates.containsKey(p1) && previousStates.contains(p2)) {
                return 1;
            } else {
                previousStates.put(p1, p2);
            }

            int num1 = p1.remove(0), num2 = p2.remove(0);
            int winner = 0;

            if (p1.size() >= num1 && p2.size() >= num2) {
                winner = game(clone(p1, num1), clone(p2, num2), false);
            } else {
                if (num1 > num2) {
                    winner = 1;
                } else {
                    winner = 2;
                }
            }

            if (winner == 1) {
                p1.add(num1);
                p1.add(num2);
            } else {
                p2.add(num2);
                p2.add(num1);
            }
        }

        if (countScore) {
            ArrayList<Integer> winner = new ArrayList<>(p1.size() != 0 ? p1 : p2);
            int sum = 0;
            while (winner.size() > 0) {
                int size = winner.size();
                sum += size * winner.remove(0);
            }
            return sum;
        } else {
            return p1.size() != 0 ? 1 : 2;
        }

    }

    public static ArrayList<Integer> clone(ArrayList<Integer> stack, int amount) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            out.add(stack.get(i));
        }
        return out;
    }

}
