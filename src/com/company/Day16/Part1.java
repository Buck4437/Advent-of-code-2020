package com.company.Day16;

import com.sun.jdi.ArrayReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day16\\input.txt");
        Scanner sc = new Scanner(file);

        ArrayList<String> inputLine = new ArrayList<>();

        while (sc.hasNextLine()) {
            inputLine.add(sc.nextLine());
        }

        String input = String.join("\n", inputLine);

        String[] sections = input.split("\n\n");

        String[] fieldsStr = sections[0].split("\n");
        ArrayList<Field> fields = new ArrayList<>();

        for (String fieldStr : fieldsStr){
            fields.add(new Field(fieldStr));
        }

        ArrayList<Ticket> otherTickets = new ArrayList<>();

        for (String ticket : sections[2].replaceAll("nearby tickets:\n", "").split("\n")){
            otherTickets.add(new Ticket(ticket));
        }

        int errorRate = 0;

        for (Ticket ticket : otherTickets){
            for (int num : ticket.getFieldNums()){
                boolean isValid = false;
                for (Field field : fields){
                    if (field.isInRange(num)){
                        isValid = true;
                        break;
                    }
                }
                if (!isValid){
                    errorRate += num;
                }
            }
        }

        System.out.println(errorRate);

    }
}
