package com.company.Day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) {

        String input = "614752839";
        String test = "389125467";

        long testVal = play(test);
        if (testVal == 149245887792L) {
            System.out.println(play(input));
        } else {
            System.out.printf("Expected: %s, Got: %s\n", 149245887792L, testVal);
        }


    }

    public static long play(String str) {

        long dt = System.nanoTime();

        int size = 1000000;

        ArrayList<Integer> temp = new ArrayList<>();

        for (String s : str.split("")) {
            temp.add(0, Integer.parseInt(s));
        }

        Hashtable<Integer, Node> cups = new Hashtable<>();

        Node next = null;

        for (int i = 1000000; i >= 10; i--) {
            Node tmp = new Node(i);
            cups.put(i, tmp);
            if (next != null) {
                tmp.setNextNode(next);
            }
            next = tmp;
        }

        for (int i : temp) {
            Node tmp = new Node(i);
            cups.put(i, tmp);
            tmp.setNextNode(next);
            next = tmp;
        }

        cups.get(size).setNextNode(next); //Cyclic

        int cupNum = Integer.parseInt(str.split("")[0]);

        for (int i = 0; i < 10000000; i++) {

            if (i % 100000 == 0) {
                System.out.printf("Move %s: %sms\n", i, (System.nanoTime() - dt) / 1000000);
            }

            Node cur = cups.get(cupNum);

            Node tmp = cur.next();

            ArrayList<Node> nextThree = new ArrayList<>();
            for (int x = 0; x < 3; x++) {
                nextThree.add(tmp);
                tmp = tmp.next();
            }

            //tmp is now the cup after 3 cups

            cur.setNextNode(tmp); //remove 3 cups temporarily

            int des = cupNum;

            do {
                des --;
                if (des == 0) des = size;
            } while (nextThree.contains(cups.get(des)));

            Node begin = cups.get(des);
            Node end = begin.next();
            begin.setNextNode(nextThree.get(0));
            nextThree.get(2).setNextNode(end);

            cupNum = tmp.getNum(); //the cup after 3 cups
        }

        return (long) cups.get(1).next().getNum() * (long) cups.get(1).next().next().getNum();
    }

}
