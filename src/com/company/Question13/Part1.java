package com.company.Question13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Question13\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> schedule = new ArrayList<>();

        while (sc.hasNextLine()) {
            schedule.add(sc.nextLine());
        }

        long baseTime = Long.parseLong(schedule.get(0));
        ArrayList<Long> buses = new ArrayList<>();
        for (String bus : schedule.get(1).split(",")){
            if (bus.equals("x")) continue;
            buses.add(Long.parseLong(bus));
        }

        long currentTime = baseTime;
        long required = -1;

        while (true){
            for (long bus : buses){
                if (currentTime % bus == 0){
                    required = bus;
                    break;
                }
            }
            if (required != -1){
                break;
            }
            currentTime++;
        }
        System.out.println((currentTime - baseTime) * required);
    }
}
