package ru.nsu.yukhnina;

/**
 * у ребра есть информация о вершинах которые оно соединяет и вес, пока вес
 * Jeneric, потом наверное стоит поменять на Integer.
 */
class Edge<G> {
    private G vertFrom;
    private G vertTo;
    private G weight;

    /**
     * I need it if i know all fields.
     */
    public Edge(G vert1, G vert2, G edge) {
        vertFrom = vert1;
        vertTo = vert2;
        weight = edge;
    }

    /**
     * I need it if i create null edge in one matrix.
     */
    public Edge() {
        vertFrom = null;
        vertTo = null;
        weight = null;
    }

    /**
     * Get vert from this edge go.
     */
    public G getVertFrom() {
        return vertFrom;
    }

    /**
     * Set name vert from.
     */
    public void setVertFrom(G vertFrom) {
        this.vertFrom = vertFrom;
    }

    /**
     * Return name or weight or otj=her that init edge.
     */
    public G getWeight() {
        return weight;
    }

    /**
     * Change weight edge.
     */
    public void setWeight(G weight) {
        this.weight = weight;
    }

    /**
     * return name edge to.
     */
    public G getVertTo() {
        return vertTo;
    }

    /**
     * Change vertex to name.
     */
    public void setVertTo(G vertTo) {
        this.vertTo = vertTo;
    }
}
