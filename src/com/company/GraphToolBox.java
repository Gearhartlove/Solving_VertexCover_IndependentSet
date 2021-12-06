package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author finley
 */
public class GraphToolBox {
    // return an array containing the vertex numbers of an optimal VC.
    public static int[] exactVC(Graph inputGraph) {
        return null;
    }

    // return (in polynomial time) an array containing the vertex numbers of a VC.
    public static int[] inexactVC(Graph inputGraph) {
        Vector<Integer> cover = new Vector<Integer>();

        for (int p = 0; p < inputGraph.getGraph().length; p++) {
            cover.add(p);
        }

        Vector<Integer> snapshot_cover;
        while (VertexChecker(inputGraph, cover)) {
            snapshot_cover = (Vector<Integer>) cover.clone();
            int remove_at = ThreadLocalRandom.current().nextInt(0, cover.size());
            cover.remove(remove_at);
        }

        return null;
    }

    // Strategy: go through each vertex in the graph. For each vertex, check if the current vertex or a "child" is in the
    // vertex cover. Get a "snap-shot" of the graph and then remove an element from the vertex cover one by one while
    // the vertex cover is still valid. Return the snap-shot of the graph.
    // Q: how do I check if something is a valid vertex cover?
    // A: go through each node in the graph
    //      1) check if node is a vertex cover
    //      2) check if neighbor is in a vertex cover
    //      > else { isValidCover == false };
    // TODO: debug vertex Checker with smaller graph and manuel inputs
    // TODO: fix always returning true issue???
    private static boolean VertexChecker(Graph inputGraph, Vector<Integer> cover) {
        for (int p = 0; p < inputGraph.getGraph().length; p++) {
            boolean isCovered = false;
            // check if parent vertex is part of the cover
            if (cover.contains(p)) {
                isCovered = true;
            }
            for (int c = 0; c < inputGraph.getGraph().length; c++) {
                // check if one of the children are a part of the cover
                //if (cover.contains(child))
                if (cover.contains(c)) {
                    isCovered = true;
                }
            }
            // Check if either the parent or children are in the cover, if not return false
            // because every edge (between parent and child) must have at least one node
            // in the vertex cover
            if (!isCovered) {
                return false;
            }
        }
        return true;
    }

    // return an array containing the vertex numbers of an optimal IS.
    public static int[] optimalIS(Graph inputGraph) {
        return null;
    }

    // return (in polynomial time) an array containing the vertex numbers of a IS.
    public static int[] inexactIS(Graph inputGraph) {
        return null;
    }
}
