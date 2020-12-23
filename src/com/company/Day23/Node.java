package com.company.Day23;

public class Node {

    private int num;
    private Node nextNode = null;

    public Node(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Node next() {
        return nextNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node node) {
        nextNode = node;
    }

}
