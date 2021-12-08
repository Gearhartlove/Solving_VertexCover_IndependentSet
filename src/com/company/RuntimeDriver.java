package com.company;

import java.util.Hashtable;

public class RuntimeDriver{
    public void runProgram() {
        int step = 2;
        int step_count = 10;
        int trials = 10;

        // exactVC
        double[][] exactVC_results = new double[step_count+1][trials];
        // inexactVC
        double[][] inexactVC_results = new double[step_count+1][trials];
        // exactIS
        double[][] exactIS_results = new double[step_count+1][trials];
        // inexactIS
        double[][] inexactIS_results = new double[step_count+1][trials];

        for (int s = 1; s < step_count+1; s++) {
            for (int t = 0; t < trials; t++) {
                // generate new graph for each step
                Graph graph = new Graph(s*step);
                // exact VC
                long exactVC_start = System.nanoTime();
                GraphToolBox.exactVC(graph);
                long exactVC_end = System.nanoTime();
                exactVC_results[s][t] = exactVC_end-exactVC_start;
                // inexact VC
                long inexactVC_start = System.nanoTime();
                GraphToolBox.inexactVC(graph);
                long inexactVC_end = System.nanoTime();
                inexactVC_results[s][t] = inexactVC_end-inexactVC_start;
                // exactIS
                long exactIS_start = System.nanoTime();
                GraphToolBox.optimalIS(graph);
                long exactIS_end = System.nanoTime();
                exactIS_results[s][t] = exactIS_end-exactIS_start;
                // inexactIS
                long inexactIS_start = System.nanoTime();
                GraphToolBox.inexactIS(graph);
                long inexactIS_end = System.nanoTime();
                inexactIS_results[s][t] = inexactIS_end-inexactIS_start;
            }
        }

        // calculate averages from the vertex cover
        Hashtable exactVC_hash = new Hashtable();
        Hashtable inexactVC_hash = new Hashtable();
        Hashtable exactIS_hash = new Hashtable();
        Hashtable inexactIS_hash = new Hashtable();
        for (int i = 1; i < step_count+1; i++) {
            double exactVC_avg = 0;
            double inexactVC_avg = 0;
            double exactIS_avg = 0;
            double inexactIS_avg = 0;
            for (int r = 0; r < trials; r++) {
                exactVC_avg += exactVC_results[i][r];
                inexactVC_avg += inexactVC_results[i][r];
                exactIS_avg += exactIS_results[i][r];
                inexactIS_avg += inexactIS_results[i][r];

            }

            // exact VC
            exactVC_avg /= trials;
            exactVC_hash.put(i*step, exactVC_avg);
            // inexact VC
            inexactVC_avg /= trials;
            inexactVC_hash.put(i*step, inexactVC_avg);
            // exact IS
            exactIS_avg /= trials;
            exactIS_hash.put(i*step, exactIS_avg);
            // inexact IS
            inexactIS_avg /= trials;
            inexactIS_hash.put(i*step, inexactIS_avg);
        }

        System.out.println("done");

        // calculate averages

    }
}

