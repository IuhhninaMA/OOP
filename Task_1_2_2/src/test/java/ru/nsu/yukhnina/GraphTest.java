package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class GraphTest {

    @Test
    void vertexNotInGraphAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertNull(adM.getVert(5));
    }

    @Test
    void vertexInGraphAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).vert);
        assertNotEquals(null, adM.getVert(2));
        assertEquals(2, adM.getVert(2).vert);
        adM.setVert(4, 5);
        assertEquals(5, adM.getVert(5).vert);
        assertNull(adM.getVert(4));
    }

    @Test
    void setNullVertexAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("I");
        adM.addVert("want");
        adM.addVert("eat");
        adM.addVert("a");
        adM.addVert("lot");
        adM.setVert("study", "free time");
        assertNull(adM.getVert("free time"));
    }

    @Test
    void addEdgeAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        assertEquals("grust'", adM.getEdge("dormitoty", "NSU").weight);
    }

    @Test
    void setEdgeAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("dormitoty");
        adM.addVert("NSU");
        adM.addEdge("dormitoty", "NSU", "grust'");
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").weight);
    }

    @Test
    void setEdgeWithoutVertAdm() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.setEdge("dormitoty", "NSU", "Wuhu Java");
        assertEquals("Wuhu Java", adM.getEdge("dormitoty", "NSU").weight);
    }

    @Test
    void deleteVertAdm() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertEquals(1, adM.getVert(1).vert);
        adM.deleteVert(1);
        adM.deleteVert(2);
        adM.deleteVert(3);
        adM.deleteVert(4);
        assertNull(adM.getVert(1));
        assertNull(adM.getVert(2));
        assertNull(adM.getVert(3));
        assertNull(adM.getVert(4));
        assertNull(adM.getVert(35));
    }
}