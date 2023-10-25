package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void vertexNotInGraph() {
        AdjacencyMatrix<Integer> adM = new AdjacencyMatrix<Integer>();
        adM.addVert(1);
        adM.addVert(2);
        adM.addVert(3);
        adM.addVert(4);
        assertNull(adM.getVert(5));
    }

    @Test
    void vertexInGraph() {
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
    void setNullVertex() {
        AdjacencyMatrix<String> adM = new AdjacencyMatrix<String>();
        adM.addVert("I");
        adM.addVert("want");
        adM.addVert("eat");
        adM.addVert("a");
        adM.addVert("lot");
        adM.setVert("study", "free time");
        assertNull(adM.getVert("free time"));
    }

}