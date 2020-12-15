package com.company.Day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Long> numbers = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\com\\company\\Day09\\input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            numbers.add(Long.parseLong(sc.nextLine()));
        }

        long invalidNum = 0;

        for (int i = 25; i < numbers.size(); i++){
            long currentNum = numbers.get(i);
            if (!isSumOf2NumFromArray(currentNum, numbers.subList(i - 25, i))){
                invalidNum = currentNum;
                break;
            }
        }

        System.out.println("Invalid num: " + invalidNum);

        for (int i = 0; i < numbers.size() - 1; i++){
            for (int j = i + 1; j < numbers.size(); j++){
                List<Long> subArray = numbers.subList(i, j + 1);
                if (invalidNum == sum(subArray)){
                    Collections.sort(subArray);
                    long small = subArray.get(0), big = subArray.get(subArray.size() - 1);
                    System.out.printf("Smallest num: %s\nBiggest num: %s\nTheir sum: %s\n", small, big, small + big);
                }
            }
        }
    }

    public static boolean isSumOf2NumFromArray(long num, List<Long> array) {
        for (int i = 0; i < array.size() - 1; i++){
            for (int j = i + 1; j < array.size(); j++){
                if (array.get(i) + array.get(j) == num) return true;
            }
        }
        return false;
    }

    public static long sum(List<Long> numbers){
        long total = 0;
        for (long number : numbers) total += number;
        return total;
    }
}
