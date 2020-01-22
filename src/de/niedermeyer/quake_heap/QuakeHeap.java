package de.niedermeyer.quake_heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QuakeHeap {

    // linked lists of roots at level i
    private List<LinkedList<Node>> T = new ArrayList<>();

    // number of nodes at level i
    private List<Integer> n = new ArrayList<>();

    public QuakeHeap() {
        T.add(new LinkedList<>());
        n.add(0);
    }

    public Leaf insert(int key, int value) {
        Leaf entry = new Leaf(key, value);

        // update bookkeeping information
        T.get(0).add(entry);
        n.set(0, n.get(0) + 1);

        return entry;
    }

    public Leaf extractMin() {
        Leaf minimumLeaf = findMin();
        deleteMin(minimumLeaf);
        consolidate();
        quake();
        return minimumLeaf;
    }

    public void decreaseKey(Leaf entry, int newKey) throws Exception {
        if (newKey >= entry.getKey()) {
            throw new Exception("Operation denied: You can not increase keys");
        }
        //TODO check if newKey already exists

        Node valueTop = entry.getValueTop();
        if (valueTop.getParent() != null) {
            // node is not root
            // cut
            cut(valueTop);
        }
        entry.setKey(newKey);
    }

    @Override
    public String toString() {
        return T.stream()
                .map(LinkedList<Node>::toString)
                .collect(Collectors.joining("\t"));
    }

    private Leaf findMin() {
        Leaf min = null;
        for (LinkedList<Node> roots : T) {
            for (Node root : roots) {
                Leaf treeMinimum = root.getLeaf();

                if (min == null || treeMinimum.getKey() < min.getKey()) {
                    min = treeMinimum;
                }
            }
        }

        return min;
    }

    private void deleteMin(Leaf minLeaf) {
        int minimum = minLeaf.getKey();

        Node currentNode = minLeaf.getValueTop();
        int currentLevel = currentNode.getLevel();

        while (currentLevel > 0) {
            T.get(currentLevel).remove(currentNode);
            n.set(currentLevel, n.get(currentLevel) - 1);

            currentLevel--;
            Node[] children = currentNode.getChildren();
            for (Node child : children) {
                T.get(currentLevel).add(child);
                if (child.getLeaf().getKey() == minimum) {
                    currentNode = child;
                }
            }
        }

        // remove leaf
        if (currentNode instanceof Leaf) {
            T.get(currentLevel).remove(currentNode);
            n.set(currentLevel, n.get(currentLevel) - 1);
        }
    }

    private void consolidate() {
        for (int i = 0; i < T.size(); i++) {
            LinkedList<Node> roots = T.get(i);
            while (roots.size() > 1) {
                link(roots.get(0), roots.get(1));
            }
        }
    }

    private void quake() {
        boolean doQuake = false;
        for (int i = 0; i < n.size() - 1; i++) {
            if (doQuake) {
                n.set(i, 0);
            } else {
                if (n.get(i + 1) > 3 / 4 * n.get(i)) {
                    doQuake = true;
                }
            }
        }
    }

    private void cut(Node node) {
        // cut edge from parent
        Node parent = node.getParent();
        if (parent != null) {
            Node[] children = parent.getChildren();
            for (int i = 0; i < children.length; i++) {
                if (children[i].equals(node)) {
                    children[i] = null;
                }
            }

            // cut edge to parent
            node.setParent(null);

            // update T
            T.get(node.getLevel()).add(node);
        }
    }

    private void link(Node root1, Node root2) {
        int currentLevel = root1.getLevel();
        // new root
        Node newRoot;
        if (root1.getLeaf().getKey() < root2.getLeaf().getKey()) {
            newRoot = new Node(root1.getLeaf(), currentLevel + 1, null, new Node[]{root1, root2});
            // update value-top-pointer of leaf
            root1.getLeaf().setValueTop(newRoot);
        } else {
            newRoot = new Node(root2.getLeaf(), currentLevel + 1, null, new Node[]{root1, root2});
            // update value-top-pointer of leaf
            root2.getLeaf().setValueTop(newRoot);
        }

        // update parent pointer of given nodes
        root1.setParent(newRoot);
        root2.setParent(newRoot);

        // update T
        T.get(currentLevel).remove(root1);
        T.get(currentLevel).remove(root2);
        if (T.size() == currentLevel + 1) {
            T.add(currentLevel + 1, new LinkedList<>());
        }
        T.get(currentLevel + 1).add(newRoot);

        //update n
        if (n.size() == currentLevel + 1) {
            n.add(currentLevel + 1, 0);
        }
        n.set(currentLevel + 1, n.get(currentLevel + 1) + 1);
    }
}
