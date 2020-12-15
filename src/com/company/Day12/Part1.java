package com.company.Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {

    final static int NORTH = 211;
    final static int EAST = 666;
    final static int SOUTH = 1116;
    final static int WEST = 16383;

    final static ArrayList<Integer> dirs = new ArrayList<>(Arrays.asList(NORTH, EAST, SOUTH, WEST));

    static int[] ship = {0, 0}; // x, y
    static int shipDir = EAST;

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day12\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> instructions = new ArrayList<>();

        while (sc.hasNextLine()){
            instructions.add(sc.nextLine());
        }

        for (String instruction : instructions){
            char op = instruction.charAt(0);
            int mag = Integer.parseInt(instruction.substring(1));
            switch (op){
                case 'N':
                    move(NORTH, mag);
                    break;
                case 'E':
                    move(EAST, mag);
                    break;
                case 'S':
                    move(SOUTH, mag);
                    break;
                case 'W':
                    move(WEST, mag);
                    break;
                case 'L':
                    turn(-mag);
                    break;
                case 'R':
                    turn(mag);
                    break;
                case 'F':
                    move(shipDir, mag);
                    break;
                default:
            }
        }

        System.out.printf("Magnitude: %s + %s = %s\n",
                Math.abs(ship[0]),
                Math.abs(ship[1]),
                Math.abs(ship[0]) + Math.abs(ship[1]));
    }

    public static void move(int dir, int mag) {
        switch (dir){
            case NORTH:
                ship[1] += mag;
                break;
            case EAST:
                ship[0] += mag;
                break;
            case SOUTH:
                ship[1] -= mag;
                break;
            case WEST:
                ship[0] -= mag;
                break;
            default:
        }
    }

    public static void turn(int mag) {
        int i = dirs.indexOf(shipDir);
        i = Math.floorMod(i + mag/90, 4);
        shipDir = dirs.get(i);
    }
}
