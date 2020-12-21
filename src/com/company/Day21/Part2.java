package com.company.Day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day21\\input.txt");
        Scanner sc = new Scanner(file);

        Hashtable<String, ArrayList<String>> allergies = new Hashtable<>();

        while (sc.hasNextLine()) {
            String recipe = sc.nextLine();
            ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(recipe.split("\\(")[0].split(" ")));
            for (String allergy : recipe.replaceAll("\\)", "").split("\\(contains ")[1].split(", ")) {
                if (allergies.containsKey(allergy)) {
                    ArrayList<String> oldCandidates = allergies.get(allergy);
                    ArrayList<String> newCandidates = new ArrayList<>();
                    for (String cand : oldCandidates) {
                        if (ingredients.contains(cand)) {
                            newCandidates.add(cand);
                        }
                    }
                    allergies.put(allergy, newCandidates);
                } else {
                    allergies.put(allergy, ingredients);
                }
            }
        }

        Enumeration<String> keys = allergies.keys();

        //iterate using while loop
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key);
            System.out.println(allergies.get(key));
        }

//        dairy
//                [kllgt]
//        eggs
//                [jrnqx]
//        fish
//                [ljvx]
//        nuts
//                [zxstb]
//        peanuts
//                [gnbxs]
//        sesame
//                [mhtc]
//        soy
//                [hfdxb]
//        wheat
//                [hbfnkq]
//
//        => kllgt,jrnqx,ljvx,zxstb,gnbxs,mhtc,hfdxb,hbfnkq
    }

}
