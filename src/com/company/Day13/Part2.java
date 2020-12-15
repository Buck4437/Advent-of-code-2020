package com.company.Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day13\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> schedule = new ArrayList<>();

        while (sc.hasNextLine()) {
            schedule.add(sc.nextLine());
        }

        String[] buses = schedule.get(1).split(",");

        long currentTime = 0;

        for (int i = 0; i < buses.length; i++){
            String bus = buses[i];
            if (bus.equals("x")) continue;
            while ((currentTime + i) % Long.parseLong(bus) != 0){
                long factor = 1;
                for (int j = 0; j < i; j++){ // all inputs are prime numbers, no need for LCM algo, phew!
                    String tmp = buses[j];
                    if (tmp.equals("x")){
                        continue;
                    }
                    factor *= Long.parseLong(tmp);
                }
                currentTime += factor; //adds LCM of all prev bus ids to make sure it still satisfy prev bus ids
            }
        }

        System.out.println(currentTime);
    }

}
