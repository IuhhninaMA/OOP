package ru.nsu.yukhnina;

/**
 * у ребра есть информация о вершинах которые оно соединяет и вес, пока вес
 * Jeneric, потом наверное стоит поменять на Integer.
 */
class Edge<G> {
    private G vertFrom;
    private G vertTo;
    private G weight;

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

    public G getVertFrom() {
        return vertFrom;
    }

    public void setVertFrom(G vertFrom) {
        this.vertFrom = vertFrom;
    }

    public G getWeight() {
        return weight;
    }

    public void setWeight(G weight) {
        this.weight = weight;
    }

    public G getVertTo() {
        return vertTo;
    }

    public void setVertTo(G vertTo) {
        this.vertTo = vertTo;
    }
}
