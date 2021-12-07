package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        String filePath = "/Users/gearhart/Desktop/MiscPrograms/TheoryProj3/src/graphs/";
        Graph graph_test = new Graph(filePath + "testgraph");
        // test the inexactVC
        GraphToolBox.inexactVC(graph_test);
    }
}
