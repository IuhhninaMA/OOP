package ru.nsu.yukhnina;

import java.util.ArrayList;

public class FindWay<G> {
    ArrayList<Integer> ways;
    ArrayList<Integer> used;

    public FindWay(G vert, Graph<G> graph) {
        ways = new ArrayList<>();
        used = new ArrayList<>();

        for (int i = 0; i < graph.getVertices().size(); i++) {
            ways.add(Integer.MAX_VALUE);
            used.add(0);
        }

        ways.set(graph.findId(vert), 0);
        Dijkstra(graph);
    }

    private void Dijkstra(Graph<G> graph) {
        Integer v;

        for (int i = 0; i < graph.getVertices().size(); i++) {
            v = null;

            for (int j = 0; j < graph.getVertices().size(); j++) {
                if (used.get(j) == 0 && (v == null || ways.get(j) < ways.get(v))) {
                    v = j;
                }
            }

            if (ways.get(v) == Integer.MAX_VALUE) {
                break;
            }

            used.set(v, 1);
            Vertex<G> vert = graph.getVertices().get(v);
            for (Vertex<G> curr_vert : graph.getVertices()) {
                Edge<G> edge = graph.getEdge(vert.getVert(), curr_vert.getVert());

                if (edge != null && ways.get(v) + (Integer) edge.getWeight() < ways.get(graph.findId(curr_vert.getVert()))) {
                    ways.set(graph.findId(curr_vert.getVert()), ways.get(v) + (Integer) edge.getWeight());
                }
            }
        }
    }

    public Integer getWay(Graph<G> g, G vert2) {
        return ways.get(g.findId(vert2));
    }
}
