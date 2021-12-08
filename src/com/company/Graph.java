package com.company;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yaw and finley
 */
public class Graph {

    private int[][] graph; // Must be of the form [vertex][vertexNeighbor1, vertexNeighbor2, ...]

    public Graph(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            if (line.startsWith("numVert")) {
                graph = new int[Integer.parseInt(line.substring(line.indexOf(" ") + 1))][];
                line = br.readLine();
                line = br.readLine();

                while (line != null) {
                    String[] values = line.split(" ");
                    int vertexNum = Integer.parseInt(values[0]);
                    int numNeighbors = Integer.parseInt(values[1]);
                    graph[vertexNum] = new int[numNeighbors];
                    for (int i = 0; i < numNeighbors; i++) {
                        graph[vertexNum][i] = Integer.parseInt(values[2 + i]);
                    }
                    line = br.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Try putting file in same filder as src folder.");
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.toString());
        }
    }

    // Strategy: connect the first vertex to every other vertex, then for each vertex, randomly generate a number
    // between 0 and num_vert and connect the queried vertex to those vertexes
    public Graph(int num_vert) {
        if (num_vert <= 0) {
            throw new RuntimeException("Number of Verteces in a graph cannot be less than: " + num_vert + ". They" +
                    "must be larger than or equal to 1.");
        }
        // how do I generate a graph with edges ?
        graph = new int[num_vert][];
        graph[0] = new int[num_vert]; // - 1 because it doesn't include itself
        for (int v = 0; v < graph.length; v++) {
            // connect first vertex to every other node
            graph[0][v] = v;
        }

        for (int v = 1; v < graph.length; v++) {
            int rand_num = ThreadLocalRandom.current().nextInt(1, num_vert);
            graph[v] = new int[rand_num];
            graph[v][0] = 0;
            int index = 1;
            for (int e = 1; e < graph[v].length; e++) {
                if (e == v) {
                    graph[v][e] = ++index;
                    index++;
                } else {
                    graph[v][e] = index++;
                }
            }
        }
    }

    public int[][] getGraph() {
        return graph;
    }
}
