package com.company.Day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Part1 {

    static Hashtable<String, String> rules = new Hashtable<>();

    public static void main(String[] args) throws FileNotFoundException {

//        String test = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2";
//
//        System.out.println(eval(test) == 13632 ? "Test passed" : eval(test) + " is not 13632, oof");

        File file = new File("src\\com\\company\\Day19\\input1.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> ln = new ArrayList<>();

        while (sc.hasNextLine()) {
            ln.add(sc.nextLine());
        }

        String input = String.join("\n", ln);

        for (String rule : input.split("\n\n")[0].split("\n")) {
            String[] tmp = rule.split(": ");
            rules.put(tmp[0], tmp[1].replaceAll("\"", ""));
        }
//
//        System.out.println(eval("0"));
//
        ArrayList<String> valid = eval("0");

        int c = 0;

        for (String msg : input.split("\n\n")[1].split("\n")) {
            if (valid.contains(msg)) c++;
        }

        System.out.println(c);

    }

    public static ArrayList<String> eval(String id) {
        ArrayList<String> out = new ArrayList<>();
        String rule = rules.get(id);
        if (rule.equals("a") || rule.equals("b")) {
            out.add(rule);
            return out;
        } else {
            for (String subRule : rule.split(" \\| ")) {
                ArrayList<ArrayList<String>> tmp = new ArrayList<>();
                for (String subId : subRule.split(" ")) {
                    tmp.add(eval(subId));
                }
                while (tmp.size() > 1) {
                    ArrayList<String> tmp2 = new ArrayList<>();
                    for (String part1 : tmp.remove(0)) {
                        for (String part2 : tmp.get(0)) {
                            tmp2.add(part1 + part2);
                        }
                    }
                    tmp.remove(0);
                    tmp.add(0, tmp2);
                }
                out.addAll(tmp.get(0));
            }
            return out;
        }
    }
}
