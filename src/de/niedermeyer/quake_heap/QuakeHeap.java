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

    public int deleteMin() {
        Leaf minimumLeaf = findMinimum();
        int minimumValue = minimumLeaf.getValue();
        deleteMinimum(minimumLeaf);
        consolidate();
        quake();
        return minimumValue;
    }

    @Override
    public String toString() {
        return T.stream()
                .map(LinkedList<Node>::toString)
                .collect(Collectors.joining("\t"));
    }

    private Leaf findMinimum() {
        Leaf minimum = new Leaf(-1, -1);
        for (LinkedList<Node> roots : T) {
            for (Node root : roots) {
                Leaf treeMinimum = root.getMinimum();

                if (minimum.getKey() == -1 || treeMinimum.getKey() < minimum.getKey()) {
                    minimum = treeMinimum;
                }
            }
        }

        return minimum;
    }

    private void deleteMinimum(Leaf minimumLeaf) {
        int minimum = minimumLeaf.getKey();

        Node currentNode = minimumLeaf.getHighestParent();
        int currentLevel = currentNode.getLevel();

        while (currentLevel > 0) {
            T.get(currentLevel).remove(currentNode);
            n.set(currentLevel, n.get(currentLevel) - 1);

            currentLevel--;
            Node[] children = currentNode.getChildren();
            for (int i = 0; i < children.length; i++) {
                T.get(currentLevel).add(children[i]);
                if (children[i].getMinimum().getKey() == minimum) {
                    currentNode = children[i];
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
                Node t1 = roots.get(0);
                Node t2 = roots.get(1);

                Node newRoot = null;
                if (t1.getMinimum().getKey() < t2.getMinimum().getKey()) {
                    newRoot = new Node(t1.getMinimum(), i + 1, new Node[]{t1, t2});
                    t1.getMinimum().setHighestParent(newRoot);
                } else {
                    newRoot = new Node(t2.getMinimum(), i + 1, new Node[]{t1, t2});
                    t2.getMinimum().setHighestParent(newRoot);
                }

                if (T.size() == i + 1) {
                    T.add(i + 1, new LinkedList<>());
                }
                if (n.size() == i + 1) {
                    n.add(i + 1, 0);
                }

                T.get(i + 1).add(newRoot);
                n.set(i + 1, n.get(i + 1) + 1);
                T.get(i).remove(t1);
                T.get(i).remove(t2);
            }
        }
    }

    private void quake() {
        boolean doQuake = false;
        for (int i = 0; i < n.size() - 1; i++) {
            if (doQuake) {
                n.set(i, 0);
            } else {
                if (n.get(i + 1) > 3 / 4 * n.get(0)) {
                    doQuake = true;
                }
            }
        }
    }
}
