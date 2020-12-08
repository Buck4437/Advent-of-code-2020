package com.company.Question02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Question02\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> passwordPolicyList = new ArrayList<>();
        while (sc.hasNextLine()){
            passwordPolicyList.add(sc.nextLine());
        }

        int valid1 = 0, valid2 = 0;
        for (String passwordPolicy : passwordPolicyList){
            Password password = new Password(passwordPolicy);
            if (password.isValid(1)){
                valid1 ++;
            }
            if (password.isValid(2)){
                valid2 ++;
            }
        }
        System.out.println("Valid passwords (Type 1): " + valid1);
        System.out.println("Valid passwords (Type 2): " + valid2);
    }
}
