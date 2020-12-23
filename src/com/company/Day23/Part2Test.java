package com.company.Day23;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Part2Test {

    public static void main(String[] args) throws FileNotFoundException {

        String input = "614752839";
        String test = "389125467";

        System.out.println(play(test).equals("67384529"));
        System.out.println(play(input));

    }

    public static String play(String str) {
        ArrayList<Integer> cups = new ArrayList<>();
        for (String s : str.split("")) {
            cups.add(Integer.parseInt(s));
        }

        for (int i = 0; i < 100; i++) {
            cups = move(cups);
        }

        String tmp = "";

        while (cups.indexOf(1) != 0) {
            cups.add(cups.remove(0));
        }

        cups.remove(0);
        for (int i : cups) {
            tmp += i;
        }
        return tmp;
    }

    public static ArrayList<Integer> move(ArrayList<Integer> cups) {
        //1st cup is current cup

        int size = cups.size();
        ArrayList<Integer> inv = new ArrayList<>();

        for (int i = 0; i <= 2; i++) {
            inv.add(cups.remove(1));
        }

        int destination = cups.get(0);
        do {
            destination --;
            if (destination == 0) destination = size;
        } while (inv.contains(destination));

        int i = cups.indexOf(destination);
        for (int j = 0; j < 3; j++) {
            cups.add(i + j + 1, inv.remove(0));
        }
        cups.add(cups.remove(0)); //Move 1st cup to last pos

        return cups;
    }

}
