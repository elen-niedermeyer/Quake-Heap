package de.niedermeyer.quake_heap;

public class Node {

    protected int level;

    protected Leaf minimum;

    private Node[] children = new Node[2];

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) throws Exception {
        this.level = level;
    }

    public Node getMinimum() {
        return minimum;
    }

    public void setMinimum(Leaf minimum) throws Exception {
        this.minimum = minimum;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) throws Exception {
        this.children = children;
    }

    @Override
    public String toString() {
        return String.valueOf(minimum.getKey());
    }
}
