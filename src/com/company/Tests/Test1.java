package com.company.Tests;

import com.company.Graph;
import com.company.GraphToolBox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class Test1 {
    @Test
    public void CorrectVertexCover() {
        // set up the correct graph
        String filePath = "/Users/gearhart/Desktop/MiscPrograms/TheoryProj3/src/graphs/";
        Graph graph = new Graph(filePath + "testgraph");
        Vector<Integer> cover;
        // Start Tests
        // all
        cover = new Vector<Integer>();
        cover.add(0); cover.add(1); cover.add(2); cover.add(3); cover.add(4);
        Assertions.assertTrue(GraphToolBox.VertexCoverChecker(graph, cover));
        // 4 vertexes
        cover = new Vector<Integer>();
        cover.add(0); cover.add(1); cover.add(2); cover.add(3);
        assertTrue(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(1); cover.add(2); cover.add(3); cover.add(4);
        assertTrue(GraphToolBox.VertexCoverChecker(graph, cover));
        // 3 vertexes
        cover = new Vector<Integer>();
        cover.add(1); cover.add(2); cover.add(4);
        assertTrue(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(1); cover.add(2); cover.add(4);
        assertTrue(GraphToolBox.VertexCoverChecker(graph, cover));
    }

    @Test
    public void IncorrectVertexCover() {
        // set up the correct graph
        String filePath = "/Users/gearhart/Desktop/MiscPrograms/TheoryProj3/src/graphs/";
        Graph graph = new Graph(filePath + "testgraph");
        Vector<Integer> cover;
        // Start Tests
        // no vertexes
        cover = new Vector<Integer>();
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        // 1 vertex
        cover = new Vector<Integer>();
        cover.add(1);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(4);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(3);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        // 2 vertexes
        cover = new Vector<Integer>();
        cover.add(3); cover.add(1);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(3); cover.add(0);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(2); cover.add(1);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        // 3 vertexes
        cover = new Vector<Integer>();
        cover.add(0); cover.add(1); cover.add(3);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(0); cover.add(1); cover.add(4);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(0); cover.add(1); cover.add(2);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
        cover = new Vector<Integer>();
        cover.add(2); cover.add(4); cover.add(3);
        assertFalse(GraphToolBox.VertexCoverChecker(graph, cover));
    }
}
