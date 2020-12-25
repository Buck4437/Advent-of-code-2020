package com.company.Day25;

public class Main {

    public static void main(String[] args) {
        long k1 = 14012298, k2 = 74241, c = 1;
        int l1 = -1, l2 = -1;

        for (int i = 1; l1 == -1 || l2 == -1; i++) {
            c *= 7;
            c %= 20201227;
            if (c == k1) {
                l1 = i;
            }
            if (c == k2) {
                l2 = i;
            }
        }

        System.out.println(l1 + " " + l2);

        c = 1;

        for (int i = 0; i < l1; i++) {
            c *= k2;
            c %= 20201227;
        }

        System.out.println(c);
    }

}
