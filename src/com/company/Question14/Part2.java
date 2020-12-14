package com.company.Question14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Question14\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> instructions = new ArrayList<>();

        while (sc.hasNextLine()) {
            instructions.add(sc.nextLine());
        }

        String mask = "";
        Hashtable<Long, Long> memory = new Hashtable<>();

        for (String instruction : instructions){
            String[] exp = instruction.split(" = ");
            String op = exp[0];
            String arg = exp[1];
            if (op.contains("mask")){
                mask = arg;
            } else if (op.contains("mem")){
                long addressBase10 = Long.parseLong(op.replaceAll("[me\\[\\]]", ""));

                String maskedLocation = "";
                for (int i = 0; i < mask.length(); i++){
                    char bit = mask.charAt(i);
                    String originalBit = Math.floor(addressBase10 / Math.pow(2, mask.length() - i - 1)) % 2 == 1 ? "1" : "0";
                    switch (bit){
                        case '0':
                            maskedLocation += originalBit;
                            break;
                        case '1':
                            maskedLocation += "1";
                            break;
                        case 'X':
                        default:
                            maskedLocation += "X";
                    }
                }

                for (String possibleAddress : idk(maskedLocation.replaceAll("\\.", ""))){
                    memory.put(Long.parseLong(possibleAddress, 2), Long.parseLong(arg));
                }
            }
        }

        long sum = 0;

        Enumeration values = memory.elements();

        while (values.hasMoreElements()) {
            sum += Long.parseLong(String.valueOf(values.nextElement()), 10);
        }

        System.out.println(sum + " (Expected value: 3434009980379)");
    }

    public static ArrayList<String> idk(String arg){
        ArrayList<String> out = new ArrayList<>();
        if (!arg.contains("X")){
            out.add(arg);
        } else {
            out.addAll(idk(arg.replaceFirst("X", "0")));
            out.addAll(idk(arg.replaceFirst("X", "1")));
        }
        return out;
    }
}
