package de.niedermeyer.quake_heap;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Leaf> entries = new ArrayList<>();

    public static void main(String[] args) {
        QuakeHeap quakeHeap = new QuakeHeap();

        System.out.println(quakeHeap);
        entries.add(quakeHeap.insert(2, 2));
        System.out.println(quakeHeap);
        entries.add(quakeHeap.insert(3, 3));
        entries.add(quakeHeap.insert(4, 4));
        System.out.println(quakeHeap);
        quakeHeap.deleteMin();
        System.out.println(quakeHeap);
        quakeHeap.deleteMin();
        System.out.println(quakeHeap);
    }
}
