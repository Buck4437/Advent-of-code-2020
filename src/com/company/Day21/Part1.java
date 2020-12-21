package com.company.Day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Day21\\input.txt");
        Scanner sc = new Scanner(file);

        Hashtable<String, Integer> ingredientOccurrence  = new Hashtable<>();
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

            for (String ingredient : ingredients) {
                if (ingredientOccurrence.containsKey(ingredient)) {
                    ingredientOccurrence.put(ingredient, ingredientOccurrence.get(ingredient) + 1);
                } else {
                    ingredientOccurrence.put(ingredient, 1);
                }
            }
        }

        Set<String> unsafeIngredients = new HashSet<>();

        //get enumeration of all keys using keys method
        Enumeration<String> keys = allergies.keys();

        //iterate using while loop
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            for (String allergy : allergies.get(key)) {
                unsafeIngredients.add(allergy);
            }
            System.out.println(key);
            System.out.println(allergies.get(key));
        }

        Enumeration<String> keys2 = ingredientOccurrence.keys();

        int sum = 0;

        //iterate using while loop
        while (keys2.hasMoreElements()) {
            String key = keys2.nextElement();
            if (!unsafeIngredients.contains(key)) {
                sum += ingredientOccurrence.get(key);
            }

            System.out.println(key + " " + ingredientOccurrence.get(key));
        }

        System.out.println(sum);

    }

}
