package de.niedermeyer.quake_heap;

public class Leaf extends Node {

    private int key;
    private int value;

    private Node highestParent;

    Leaf(int key, int value) {
        this.key = key;
        this.value = value;

        this.level = 0;
        this.minimum = null;
    }

    @Override
    public void setChildren(Node[] children) throws Exception {
        throw new Exception("You are not allowed to set children on leaves.");
    }

    @Override
    public void setLevel(int level) throws Exception {
        throw new Exception("You are not allowed to change the level of a leaf.");
    }

    @Override
    public void setMinimum(Leaf minimum) throws Exception {
        throw new Exception("You are not allowed to change the minimum pointer of a leaf.");
    }

    public int getKey() {
        return key;
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