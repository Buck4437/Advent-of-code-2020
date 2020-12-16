package com.company.Day16;

import com.sun.jdi.ArrayReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Part2 {

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

        for (String fieldStr : fieldsStr) {
            fields.add(new Field(fieldStr));
        }

        Ticket yourTicket = new Ticket(sections[1].split("\n")[1]);

        ArrayList<Ticket> otherTickets = new ArrayList<>();

        for (String ticket : sections[2].replaceAll("nearby tickets:\n", "").split("\n")) {
            otherTickets.add(new Ticket(ticket));
        }

        ArrayList<Ticket> validTickets = new ArrayList<>();

        for (Ticket ticket : otherTickets) {
            boolean isTicketValid = true;
            for (int num : ticket.getFieldNums()) {
                boolean isValid = false;
                for (Field field : fields) {
                    if (field.isInRange(num)) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid){
                    isTicketValid = false;
                    break;
                }
            }
            if (isTicketValid) {
                validTickets.add(ticket);
            }
        }

        for (Field field : fields) {
            ArrayList<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < fields.size(); i++) {
                boolean valid = true;
                for (Ticket ticket : validTickets) {
                    int num = ticket.getFieldNums().get(i);
                    if (!field.isInRange(num)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    candidates.add(i);
                }
            }
            System.out.printf("%s: %s\n", field.getName(), candidates);
            field.setCandidates(candidates);
        }

        while (unassignedFields(fields).size() > 0) {
            for (Field field : unassignedFields(fields)) {
                if (field.hasOneCandidate()) {
                    int pos = field.getCandidates().get(0);
                    field.setPos(pos);
                    System.out.printf("%s: %s\n", field.getName(), pos);

                    for (Field field2 : unassignedFields(fields)) {
                        field2.removeCandidate(pos);
                    }
                }
            }
        }

        long mul = 1;

        for (Field field : fields){
            if (field.getName().contains("departure")){
                int pos = field.getPos();
                int num = yourTicket.getFieldNums().get(pos);
                mul *= num;
            }
        }

        System.out.println(mul);

    }

    public static ArrayList<Field> unassignedFields(ArrayList<Field> fields) {
        ArrayList<Field> unassigned = new ArrayList<>();
        for (Field field : fields){
            if (!field.hasPos()){
                unassigned.add(field);
            }
        }
        return unassigned;
    }
}
