package ru.nsu.yukhnina;

/**
 * вершина хранит в себе только имя, раньше были ещё
 * количество исходящих и входящих рёбер, но они бесполезны.
 */
public class Vertex <G> {
    public G vert;

    public Vertex(G vertNew) {
        vert = vertNew;
    }
}
