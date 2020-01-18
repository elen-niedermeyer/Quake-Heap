package de.niedermeyer.quake_heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QuakeHeap {

    private List<TournamentTree> trees = new ArrayList<>();

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
        trees.add(new TournamentTree(entry));

        // update bookkeeping information
        T.get(0).add(entry);
        //TODO update n

        return entry;
    }

    @Override
    public String toString() {
        return trees.stream()
                .map(TournamentTree::toString)
                .collect(Collectors.joining("\t"));
    }
}
