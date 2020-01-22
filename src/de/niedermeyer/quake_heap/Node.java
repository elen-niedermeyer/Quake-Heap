package de.niedermeyer.quake_heap;

public class Node {

    protected int level;

    protected Leaf leaf;

    protected Node parent;

    private Node[] children = new Node[2];

    public Node() {
        // default constructor
    }

    public Node(Leaf leaf, int level, Node parent, Node[] children) {
        this.leaf = leaf;
        this.level = level;
        this.parent = parent;
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public Leaf getLeaf() {
        return leaf;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return String.valueOf(leaf.getKey());
    }
}
