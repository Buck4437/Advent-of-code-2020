package com.company.Question14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Question14\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> instructions = new ArrayList<>();

        while (sc.hasNextLine()) {
            instructions.add(sc.nextLine());
        }

        String mask = "";
        Hashtable<String, String> memory = new Hashtable<>();

        for (String instruction : instructions){
            String[] exp = instruction.split(" = ");
            String op = exp[0];
            String arg = exp[1];
            if (op.contains("mask")){
                mask = arg;
            } else if (op.contains("mem")){
                long argL = Long.parseLong(arg);
                String location = op.replaceAll("[me\\[\\]]", "");

                StringBuilder bytes = new StringBuilder();
                for (int i = 0; i < mask.length(); i++){
                    char bit = mask.charAt(i);
                    if (bit != 'X'){
                        bytes.append(bit);
                    } else {
                        bytes.append(Math.floor(argL / Math.pow(2, mask.length() - i - 1)) % 2 == 1 ? 1 : 0);
                    }
                }
                memory.put(location, String.valueOf(bytes));
            }
        }

        long sum = 0;

        Enumeration values = memory.elements();

        while (values.hasMoreElements()) {
            sum += Long.parseLong(String.valueOf(values.nextElement()), 2);
        }

        System.out.println(sum + " (Expected value: 6317049172545)");
    }
}
