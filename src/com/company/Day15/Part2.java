package com.company.Day15;

import java.util.*;

public class Part2 {

    public static void main(String[] args) {

        int[] startNum = {16,1,0,18,12,14,19};
        Hashtable<Integer, Integer> pos = new Hashtable<>();
        int prevNum = 0;

        for (int i = 0; i <= 30000000 - 1; i++){
            int currNum;
            if (i < startNum.length){
                currNum = startNum[i];
            } else {
                if (!pos.containsKey(prevNum)){
                    currNum = 0;
                } else {
                    currNum = i - pos.get(prevNum) - 1;
                }
            }
            if (i > 0){
                pos.put(prevNum, i - 1);
            }
            prevNum = currNum;
        }

        System.out.println(prevNum);
    }
}
