package de.niedermeyer.quake_heap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TournamentTree {

    private List<Node> nodes = new ArrayList<>();

    TournamentTree(Leaf leaf) {
        nodes.add(leaf);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void removeNode(Node node) {
        this.nodes.remove(node);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
            final int level = i;
            List<Node> nodesAtI = nodes.stream().filter(node -> node.level == level).collect(Collectors.toList());

            if (nodesAtI.size() == 0) {
                // we can break when there's no node at one level
                // there can't be another higher node
                break;
            }

            if (i != 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(nodesAtI.stream()
                    .map(Node::toString)
                    .collect(Collectors.joining("\t")));
        }
        return stringBuilder.toString();
    }
}
