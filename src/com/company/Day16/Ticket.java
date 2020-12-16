package com.company.Day16;

import java.util.ArrayList;

public class Ticket {

    private ArrayList<Integer> fieldNums = new ArrayList<>();

    public Ticket(String input){
        for (String num : input.split(",")){
            fieldNums.add(Integer.parseInt(num));
        }
    }

    public ArrayList<Integer> getFieldNums() {
        return fieldNums;
    }
}
