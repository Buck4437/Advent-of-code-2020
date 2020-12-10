package com.company.Question07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static Set<String> parentBags = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src\\com\\company\\Question07\\input.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> inputLine = new ArrayList<>();

        while (sc.hasNextLine()){
            inputLine.add(sc.nextLine());
        }

        String[] rules = String.join("\n", inputLine).replace("bags", "bag").split("\n");


        //part 1
        findParentBags("shiny gold bag", rules);
        System.out.println(parentBags.size());

        //part 2
        System.out.println(countTotalBags("shiny gold bag", rules) - 1); //to exclude shiny gold bag itself
    }

    public static void findParentBags(String bagName, String[] rules){
        for (String rule : rules){
            String[] parentChildBag = rule.split(" contain");

            if (parentChildBag[1].contains(bagName)){
                String parentName = parentChildBag[0];
                parentBags.add(parentName);
                findParentBags(parentName, rules);
            }
        }
    }

    public static int countTotalBags(String parentBagName, String[] rules){
        int bags = 1; //itself

        for (String rule : rules){
            String[] bagRules = rule.replaceAll("\\.", "")
                    .split(" contain ");

            if (bagRules[0].contains(parentBagName)){
                if (bagRules[1].contains("no other")){
                    return bags;
                }

                String[] childBags = bagRules[1].split(", ");
                for (String childBagParam : childBags){
                    int numberOfChildBags = Integer.parseInt(childBagParam.split(" ")[0]);
                    String childBagName = childBagParam.replace(numberOfChildBags + " ", "");
                    bags += numberOfChildBags * countTotalBags(childBagName, rules);
                }
                return bags;
            }
        }
        return bags;
    }
}
