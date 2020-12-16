package com.company.Day16;

import java.util.ArrayList;

public class Field {

    private final int POS_NOT_SET = -1;
    private int pos = POS_NOT_SET;

    private ArrayList<Integer> candidates = new ArrayList<>();

    private String name;
    private ArrayList<Integer> ranges = new ArrayList<>();

    public Field (String input) {
        this.name = input.split(": ")[0];

        String[] ranges = input.split(": ")[1].split("( or )|-");
        for (String rangeStr : ranges){
            this.ranges.add(Integer.parseInt(rangeStr));
        }
    }

    public boolean isInRange(int i) {
        return i >= ranges.get(0) && i <= ranges.get(1) || i >= ranges.get(2) && i <= ranges.get(3);
    }

    public ArrayList<Integer> getRanges() {
        return ranges;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public boolean hasPos() {
        return pos != POS_NOT_SET;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Integer> candidates) {
        this.candidates = candidates;
    }

    public void removeCandidate(int n) {
        this.candidates.remove(Integer.valueOf(n));
    }

    public boolean hasOneCandidate(){
        return candidates.size() == 1;
    }
}
