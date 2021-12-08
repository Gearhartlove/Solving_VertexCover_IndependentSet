package com.company;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        // apple file path
        String ApplefilePath = "/Users/gearhart/Desktop/MiscPrograms/TheoryProj3/src/graphs/";
        // windows file path
        //String WindowfilePath =  "C:/Users/krist/IdeaProjects/Solving_VertexCover_IndependentSet/src/graphs/";
        Graph graph_test = new Graph(ApplefilePath + "fourfifty");
        // test the inexactVC
//        var cover_inexact = GraphToolBox.inexactVC(graph_test);
//        var is_inexact = GraphToolBox.inexactIS(graph_test);
        var cover_exact = GraphToolBox.exactVC(graph_test);
        System.out.println("working?");
//        var is_optimal = GraphToolBox.optimalIS(graph_test);
    }
}
