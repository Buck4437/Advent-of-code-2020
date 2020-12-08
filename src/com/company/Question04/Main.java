package com.company.Question04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static int checkPassports(String[] fields, String[] passports){

        int validPassport = 0;

        for (String passport : passports){
            String passportFields = " " + passport.replace('\n', ' ') + " ";
            boolean isValid = true;
            for (String field : fields) {
                if (!Pattern.compile(field).matcher(passportFields).find()){
                    isValid = false;
                    break;
                }
            }
            if (isValid){
                System.out.println(passportFields);
                validPassport ++;
            }
        }

        return validPassport;
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Question04\\input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> inputLine = new ArrayList<>();

        while (sc.hasNextLine()){
            inputLine.add(sc.nextLine());
        }

        String[] passports = String.join("\n", inputLine).split("\n\n");

        String[] fields1 = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        String[] fields2 = {
                " byr:(19[2-9][0-9]|200[0-2]) ",
                " iyr:(201[0-9]|2020) ",
                " eyr:(202[0-9]|2030) ",
                " hgt:((1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in) ",
                " hcl:(#[0-9a-f]{6}) ",
                " ecl:(amb|blu|brn|gry|grn|hzl|oth) ",
                " pid:[0-9]{9} "
        };

        System.out.printf("Valid passports:\nPart 1: %s\nPart 2: %s",
                checkPassports(fields1, passports),
                checkPassports(fields2, passports));
    }
}
