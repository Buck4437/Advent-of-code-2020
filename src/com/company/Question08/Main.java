package com.company.Question08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int currentLineNumber = 0;
    static int accumulator = 0;
    static boolean isEndOfProgram = false;
    static ArrayList<Integer> prevLineNumbers = new ArrayList<>();

    public static void runLine(int lineNum, String[] program){
        if (lineNum + 1 > program.length) {
            isEndOfProgram = true;
            return;
        }
        String line = program[lineNum];
        String operand = line.split(" ")[0];
        int val = Integer.parseInt(line.split(" ")[1]);
        switch (operand) {
            case "acc" -> {
                accumulator += val;
                currentLineNumber++;
            }
            case "jmp" -> {
                currentLineNumber += val;
            }
            default -> {
                currentLineNumber++;
            }
        }
    }

    final static int END_OF_PROGRAM = 0;
    final static int INFINITE_LOOP = 1;

    public static int runProgram(String[] program){
        currentLineNumber = 0;
        accumulator = 0;
        isEndOfProgram = false;
        prevLineNumbers = new ArrayList<>();

        while (!prevLineNumbers.contains(currentLineNumber)){
            prevLineNumbers.add(currentLineNumber);
            runLine(currentLineNumber, program);
            if (isEndOfProgram) break;
        }

        if (isEndOfProgram){
            return END_OF_PROGRAM;
        } else {
            return INFINITE_LOOP;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\com\\company\\Question08\\input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> inputLine = new ArrayList<>();

        while (sc.hasNextLine()){
            inputLine.add(sc.nextLine());
        }

        String input = String.join("\n", inputLine);

        String[] baseProgram = input.split("\n");
        for (int i = 0; i < baseProgram.length; i++){
            String[] newProgram = input.split("\n");
            switch (newProgram[i].split(" ")[0]){
                case ("nop"):{
                    newProgram[i] = newProgram[i]
                            .replace("nop", "jmp");
                    break;
                }
                case ("jmp"):{
                    newProgram[i] = newProgram[i]
                            .replace("jmp", "nop");
                    break;
                }
            }
            int statusCode = runProgram(newProgram);
            if (statusCode == END_OF_PROGRAM){
                System.out.println(accumulator);
                break;
            }
        }
    }
}
