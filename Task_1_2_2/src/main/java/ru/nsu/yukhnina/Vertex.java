package ru.nsu.yukhnina;

/**
 * вершина хранит в себе только имя, раньше были ещё
 * количество исходящих и входящих рёбер, но они бесполезны.
 */
class Vertex<G> {
    private G vert;

    public Vertex(G vertNew) {
        vert = vertNew;
    }

    /**
     * Return vert name.
     */
    public G getVert() {
        return vert;
    }

    /**
     * Change vert name.
     */
    public void setVert(G vert) {
        this.vert = vert;
    }

}
