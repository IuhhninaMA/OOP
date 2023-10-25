package ru.nsu.yukhnina;

/**
 * у ребра есть информация о вершинах которые оно соединяет и вес, пока вес
 * Jeneric, потом наверное стоит поменять на Integer.
 */
public class Edge <G> {
    public G vertFrom;
    public G vertTo;
    public G weight;

    public Edge(G vert1, G vert2, G edge) {
        vertFrom = vert1;
        vertTo = vert2;
        weight = edge;
    }

    public Edge() {
        vertFrom = null;
        vertTo = null;
        weight = null;
    }
}
