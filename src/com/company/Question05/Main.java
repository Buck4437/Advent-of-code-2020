package com.company.Question05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static int calculateID(String boardingPass){
        String idBase2 = boardingPass.replace('F', '0')
                .replace('B', '1')
                .replace('L', '0')
                .replace('R', '1');
        return Integer.parseInt(idBase2, 2);
    }

    private static int findMissingID(ArrayList<Integer> ids){
        Collections.sort(ids);
        for (int i = 0; i < ids.size() - 1; i++){
            if (ids.get(i) + 1 != ids.get(i+1)){
                return ids.get(i) + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\com\\company\\Question05\\input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> boardingPassesList = new ArrayList<>();

        while (sc.hasNextLine()){
            boardingPassesList.add(sc.nextLine());
        }

        ArrayList<Integer> ids = new ArrayList<>();
        for (String boardingPass : boardingPassesList){
            ids.add(calculateID(boardingPass));
        }
        Collections.sort(ids);
        System.out.printf("Max ID: %s (Expected: 976)\n", ids.get(ids.size()-1));
        System.out.printf("Missing ID: %s (Expected: 685)\n", findMissingID(ids));
    }
}
