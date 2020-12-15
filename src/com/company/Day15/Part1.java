package com.company.Day15;

import java.util.*;

public class Part1 {

    public static void main(String[] args) {

        int[] startNum = {16,1,0,18,12,14,19};
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i <= 2020 - 1; i++){
            if (i < startNum.length){
                nums.add(startNum[i]);
            } else {
                Collections.reverse(nums);
                int prevNum = nums.remove(0);
                int age = nums.indexOf(prevNum) + 1; // 0 if not inside nums
                Collections.reverse(nums);
                nums.add(prevNum);
                nums.add(age);
            }
        }

        System.out.println(nums.get(2020 - 1));
    }
}
