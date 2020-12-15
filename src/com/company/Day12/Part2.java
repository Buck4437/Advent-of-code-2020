package com.company.Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    static int[] ship = {0, 0};
    static int[] waypoint = {10, 1};

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day12\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> instructions = new ArrayList<>();

        while (sc.hasNextLine()){
            instructions.add(sc.nextLine());
        }

        for (String instruction : instructions){
            char operand = instruction.charAt(0);
            int magnitude = Integer.parseInt(instruction.substring(1));
            switch (operand){
                case 'N':
                    waypoint[1] += magnitude;
                    break;
                case 'E':
                    waypoint[0] += magnitude;
                    break;
                case 'S':
                    waypoint[1] -= magnitude;
                    break;
                case 'W':
                    waypoint[0] -= magnitude;
                    break;
                case 'L':
                    for (int i = 0; i < magnitude; i += 90){
                        int temp = waypoint[0];
                        waypoint[0] = -waypoint[1];
                        waypoint[1] = temp;
                    }
                    break;
                case 'R':
                    for (int i = 0; i < magnitude; i += 90){
                        int temp = waypoint[1];
                        waypoint[1] = -waypoint[0];
                        waypoint[0] = temp;
                    }
                    break;
                case 'F':
                    ship[0] += waypoint[0] * magnitude;
                    ship[1] += waypoint[1] * magnitude;
                    break;
                default:
            }
        }

        System.out.printf("Magnitude: %s + %s = %s\n",
                Math.abs(ship[0]),
                Math.abs(ship[1]),
                Math.abs(ship[0]) + Math.abs(ship[1]));
    }

}
