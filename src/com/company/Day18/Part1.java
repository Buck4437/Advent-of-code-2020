package com.company.Day18;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        String test = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2";

        System.out.println(eval(test) == 13632 ? "Test passed" : eval(test) + " is not 13632, oof");

        File file = new File("src\\com\\company\\Day18\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> questions = new ArrayList<>();

        while (sc.hasNextLine()) {
            questions.add(sc.nextLine().replaceAll(" ", ""));
        }

        long acc = 0;

        for (String question : questions) {
            acc += eval(question);
        }

        System.out.println(acc);

    }

    final static int ADD = 0 ;
    final static int MUL = 1;

    public static long eval(String input) {

        long acc = 0;
        int op = ADD;
        long val = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '(':
                    String exp = extract(input, i);
                    val = eval(exp);
                    i += exp.length() + 1;
                    break;
                case '+':
                    acc = op(op, acc, val);
                    val = 0;
                    op = ADD;
                    break;
                case '*':
                    acc = op(op, acc, val);
                    val = 0;
                    op = MUL;
                    break;
                default:
                    val = val * 10 + Long.parseLong(String.valueOf(c));
            }
        }

        return op(op, acc, val);
    }

    private static String extract(String in, int i) {
        String out = "";
        int a = 1;
        for (char c : in.substring(i+1).toCharArray()) {
            if (c == '(') a++;
            else if (c == ')') a--;
            if (a == 0) return out;
            out += c;
        }
        return in;
    }

    private static long op(int op, long a, long b) {
        return switch (op) {
            case ADD -> a + b;
            case MUL -> a * b;
            default -> 0;
        };
    }
}
