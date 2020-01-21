package de.niedermeyer.quake_heap;

public class Node {

    protected int level;

    protected Leaf minimum;

    private Node[] children = new Node[2];

    public Node() {
        // default constructor
    }

    public Node(Leaf minimum, int level, Node[] children) {
        this.minimum = minimum;
        this.level = level;
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public Leaf getMinimum() {
        return minimum;
    }

    public Node[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return String.valueOf(minimum.getKey());
    }
}
