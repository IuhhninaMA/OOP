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

    public G getVert() {
        return vert;
    }

    public void setVert(G vert) {
        this.vert = vert;
    }
}
