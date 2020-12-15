package com.company.Day01;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day01\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<Integer> listOfNum = new ArrayList<>();
        while (sc.hasNextLine()){
            int num = Integer.parseInt(sc.nextLine());
            listOfNum.add(num);
        }

        //part 1
        for (int num : listOfNum){
            int num2 = 2020 - num;
            if (listOfNum.contains(num2)){
                System.out.printf("Number pair: %s and %s. Total: %s\n", num, num2, num * num2);
                break;
            }
        }

        //part 2
        boolean numFound = false;
        for (int num1 : listOfNum){
            for (int num2 : listOfNum) {
                int num3 = 2020 - num1 - num2;
                if (listOfNum.contains(num3)){
                    System.out.printf("Number pair: %s, %s and %s. Total: %s\n", num1, num2, num3, num1 * num2 * num3);
                    numFound = true;
                    break;
                }
            }
            if (numFound){
                break;
            }
        }
    }

}
