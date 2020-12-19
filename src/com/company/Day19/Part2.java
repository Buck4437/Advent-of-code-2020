package com.company.Day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Part2 {

    static Hashtable<String, String> rules = new Hashtable<>();

    public static void main(String[] args) throws FileNotFoundException {

        //Rule 0: 8 11 => 42*n 42*n 31*n (m >= 2, n >= 1, m > n)

        File file = new File("src\\com\\company\\Day19\\input2.txt");
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

        ArrayList<String> r42 = eval("42");
        String r42Regex = "(" + String.join("|", r42) + ")";
        System.out.println(r42Regex);
        ArrayList<String> r31 = eval("31");
        String r31Regex = "(" + String.join("|", r31) + ")";
        System.out.println(r31Regex);
        int bitLength = r42.get(0).length();

        int c = 0;

        for (String msg : input.split("\n\n")[1].split("\n")) {
            int original = msg.length();
            if (Pattern.compile(" " + r42Regex + "{2,}" + r31Regex + "{1,} ").matcher(" " + msg + " ").find()){
                int length = 0;
                while (Pattern.compile(" " + r42Regex).matcher(" " + msg).find()) {
                    length += bitLength;
                    msg = msg.substring(bitLength);
                }
                if (length > original - length) {
                    c++;
                }
            }
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
