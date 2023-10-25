package ru.nsu.yukhnina;

import java.util.ArrayList;

public interface Graph<G> {
    void addVert(G newVert);
    void deleteVert(G vert);
    void addEdge(G vert1, G vert2, G edge);
    void deleteEdge(G vert1, G vert2);
    Edge<G> getEdge(G vert1, G vert2);
    void setEdge(G vert1, G vert2, G newEdge);
    Vertex<G> getVert(G vert);
    void setVert(G oldVert, G newVert);
}


class Vertex<G>{
    public G vert;
    public Vertex(G vertNew) {
        vert = vertNew;
    }
}

class Edge<G>{
    public G vertFrom;
    public G vertTo;
    public G weight;

    public Edge(G vert1, G vert2, G edge) {
        vertFrom = vert1;
        G vertTo = vert2;
        G weight = edge;
    }
}