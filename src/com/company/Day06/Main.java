package com.company.Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\com\\company\\Day06\\input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> inputLine = new ArrayList<>();

        while (sc.hasNextLine()){
            inputLine.add(sc.nextLine());
        }

        String[] groupList = String.join("\n", inputLine).split("\n\n");

        //part 1
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int sum = 0;
        for (String group : groupList){
            for (char letter : alphabet.toCharArray()){
                if (group.indexOf(letter) != -1){
                    sum++;
                }
            }
        }
        System.out.println("Sum (Part 1): " + sum);

        //part 2
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        sum = 0;
        for (String group : groupList){
            for (char letter : alphabet.toCharArray()){
                boolean allContains = true;
                for (String person : group.split("\n")){
                    if (person.equals("")) {
                        continue;
                    }
                    if (person.indexOf(letter) == -1){
                        allContains = false;
                        break;
                    }
                }
                if (allContains){
                    sum++;
                }
            }
        }
        System.out.println("Sum (Part 2): " + sum);
    }
}
