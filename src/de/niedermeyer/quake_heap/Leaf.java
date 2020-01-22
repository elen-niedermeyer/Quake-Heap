package de.niedermeyer.quake_heap;

public class Leaf extends Node {

    private int key;
    private int value;

    private Node valueTop;

    Leaf(int key, int value) {
        this.key = key;
        this.value = value;

        this.level = 0;
        this.leaf = this;
        this.valueTop = this;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public Node getValueTop() {
        return valueTop;
    }

    public void setValueTop(Node valueTop) {
        this.valueTop = valueTop;
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }
}