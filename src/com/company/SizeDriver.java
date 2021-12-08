package com.company;

import java.util.Hashtable;

public class SizeDriver {
    public void runProgram() {
        int step = 2;
        int step_count = 10;
        int trials = 10;

        // exactVC
        int[][] exactVC_results = new int[step_count+1][trials];
        // inexactVC
        int[][] inexactVC_results = new int[step_count+1][trials];
        // exactIS
        int[][] exactIS_results = new int[step_count+1][trials];
        // inexactIS
        int[][] inexactIS_results = new int[step_count+1][trials];

        for (int s = 1; s < step_count+1; s++) {
            for (int t = 0; t < trials; t++) {
                // generate new graph for each step
                Graph graph = new Graph(s*step);
                // exact VC
                exactVC_results[s][t] = GraphToolBox.exactVC(graph).length;
                // inexact VC
                inexactVC_results[s][t] = GraphToolBox.inexactVC(graph).length;
                // exactIS
                exactIS_results[s][t] = GraphToolBox.optimalIS(graph).length;
                // inexactIS
                inexactIS_results[s][t] = GraphToolBox.inexactIS(graph).length;
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
