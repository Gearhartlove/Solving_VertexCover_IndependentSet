package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author finley
 */
public class GraphToolBox {
    // return an array containing the vertex numbers of an almost optimal VC.
    //Strategy:look at each node in the graph, if it or a neighbor is not marked, mark it and repeat that process
    // untill all nodes and their neighbors have been looked at.
    // Q:is this really the best strategy?
    public static int[] exactVC(Graph inputGraph) {
        // strategy : get each subset of the graph of size , while getting the subset, check if it is a valid vertex
        // cover. If it is , return the vertex cover
        Vector<Integer> graph_vertexes = new Vector<>(); //arr
        for (int v = 0; v < inputGraph.getGraph().length; v++) {
            graph_vertexes.add(v);
        }

        // start at permutations of size 1
        int graph_size = graph_vertexes.size(); // n
        int comb_size = 1; // r

        while (true) {
            int data[] = new int[comb_size];
            Vector<int[]> all_permutations = new Vector<>();
            CombinationUtil(graph_vertexes, graph_size, comb_size, 0, data, 0, all_permutations);
            for (int[] perm : all_permutations) {
                Vector<Integer> cover = new Vector<>();
                for (int v : perm) {
                    cover.add(v);
                }
                if (VertexCoverChecker(inputGraph, cover)) {
                    return CreateGraphSubset(cover, inputGraph);
                }
            }
            System.out.println(comb_size);
            comb_size++;
        }
    }

    private static void CombinationUtil(Vector<Integer> vertexes, int graph_size, int comb_size,
                            int index, int data[], int i, Vector<int[]> all_permutations) {
        if (index == comb_size) {
           // Vector<Integer> all_permutations
            for (int j = 0; j < comb_size; j++) {
                //System.out.print(data[j] + " ");
                var save_data = data.clone();
                all_permutations.add(save_data);
            }
            // System.out.println("");
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= graph_size) return;

        // current is included, put next at next location
        data[index] = vertexes.get(i);
        CombinationUtil(vertexes, graph_size, comb_size, index+1, data, i + 1, all_permutations);

        // current is excluded, replace it with next (Note that i+1 is passed, but index is not changed)
        CombinationUtil(vertexes, graph_size, comb_size, index, data, i+1, all_permutations);
    }

    // return (in polynomial time) an array containing the vertex numbers of a VC.
    public static int[] inexactVC(Graph inputGraph) {
        Vector<Integer> cover = new Vector<Integer>();
        // add everything to the cover to start
        for (int p = 0; p < inputGraph.getGraph().length; p++) {
            cover.add(p);
        }
        // save a snapshot of the last vertex cover each loop
        // randomly remove one of the vertexes from the vertex cover until the cover is no longer valid.
        // First loop will always be a valid vertex cover.
        Vector<Integer> snapshot_cover = new Vector<>();
        while (VertexCoverChecker(inputGraph, cover)) {
            snapshot_cover = (Vector<Integer>) cover.clone();
            int remove_at = ThreadLocalRandom.current().nextInt(0, cover.size());
            cover.remove(remove_at);
        }
        if (VertexCoverChecker(inputGraph, snapshot_cover)) {
            return CreateGraphSubset(snapshot_cover, inputGraph);
        } else {
            throw new RuntimeException("inexactVC returned a non valid vertex cover");
        }
    }

    // create int[] data structure filled with vertex numbers in the vertex cover
    private static int[] CreateGraphSubset(Vector<Integer> snapshot, Graph graph) {
        int[] cover_to_return = new int[snapshot.size()];
        for (int p = 0; p < snapshot.size(); p++) {
            cover_to_return[p] = snapshot.get(p);
        }
        return cover_to_return;
    }

    // Strategy: go through each vertex in the graph. For each vertex, check if the current vertex or a "child" is in the
    // vertex cover. Get a "snap-shot" of the graph and then remove an element from the vertex cover one by one while
    // the vertex cover is still valid. Return the snap-shot of the graph.
    // Q: how do I check if something is a valid vertex cover?
    // A: go through each node in the graph
    //      1) check if node is a vertex cover
    //      2) check if neighbor is in a vertex cover
    //      > else { isValidCover == false };
    public static boolean VertexCoverChecker(Graph inputGraph, Vector<Integer> cover) {
        for (int p = 0; p < inputGraph.getGraph().length; p++) {
            // check if parent vertex is part of the cover
            if (cover.contains(p)) {
                continue;
            } else {
                // looking at the children of the parent. Answering the Question: What is each vertex connected too?
                for (int c = 0; c < inputGraph.getGraph()[p].length; c++) {
                    boolean isCovered = false;
                    // check if one of the children are a part of the cover
                    //if (cover.contains(child))
                    if (cover.contains(inputGraph.getGraph()[p][c])) {
                        isCovered = true;
                    }
                    // Check if either the parent or children are in the cover, if not return false
                    // because every edge (between parent and child) must have at least one node
                    // in the vertex cover
                    if (!isCovered) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    // return an array containing the vertex numbers of an optimal IS.
    public static int[] optimalIS(Graph inputGraph) {
        // find the optimal vertex cover of the inputGraph and then return the compliment
        var vertex_cover = exactVC(inputGraph);
        var independent_set = new Vector<Integer>();

        for (int v = 0; v < inputGraph.getGraph().length; v++) {
            boolean inCover = false;
            for (int vc = 0; vc < vertex_cover.length; vc++) {
                if (v == vc) {
                    inCover = true;
                }
            }
            if (!inCover) {
                independent_set.add(v);
            }
        }
        if (ISChecker(inputGraph, independent_set, vertex_cover)) {
            return CreateGraphSubset(independent_set, inputGraph);
        }
        else {
            throw new RuntimeException("Failed to return valid optimal valid optimal independent set.");
        }
    }

    // return (in polynomial time) an array containing the vertex numbers of a IS.
    public static int[] inexactIS(Graph inputGraph) {
        // find the optimal vertex cover of the inputGraph and then return the compliment
        var vertex_cover = inexactVC(inputGraph);
        var independent_set = new Vector<Integer>();

        for (int v = 0; v < inputGraph.getGraph().length; v++) {
            boolean inCover = false;
            for (int vc = 0; vc < vertex_cover.length; vc++) {
                if (v == vc) {
                    inCover = true;
                }
            }
            if (!inCover) {
                independent_set.add(v);
            }
        }
        if (ISChecker(inputGraph, independent_set, vertex_cover)) {
            return CreateGraphSubset(independent_set, inputGraph);
        }
        else {
            throw new RuntimeException("Failed to return valid optimal valid optimal independent set.");
        }
    }

    // Check if valid Independent Set (IS) by testing if the sum of both VC and IS is equal to the length of graph.
    // Also check each value in the IS and if they are also in the VC return false
    private static boolean ISChecker(Graph graph, Vector<Integer> IS, int[] cover) {
        if (cover.length + IS.size() == graph.getGraph().length) {
            for (int vc = 0; vc < cover.length; vc++) {
                if (IS.contains(vc)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
