package de.niedermeyer.quake_heap;

public class Leaf extends Node {

    private int key;
    private int value;

    private Node highestParent;

    Leaf(int key, int value) {
        this.key = key;
        this.value = value;

        this.level = 0;
        this.minimum = this;
        this.highestParent = this;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public Node getHighestParent() {
        return highestParent;
    }

    public void setHighestParent(Node highestParent) {
        this.highestParent = highestParent;
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }
}