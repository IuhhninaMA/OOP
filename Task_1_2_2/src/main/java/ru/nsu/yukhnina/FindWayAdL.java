package ru.nsu.yukhnina;

import java.util.ArrayList;

public class FindWayAdL<G> {
    ArrayList<Integer> ways;
    ArrayList<Integer> used;

    public FindWayAdL(G vert, AdjacencyLists<G> adL) {
        ways = new ArrayList<>();
        used = new ArrayList<>();

        for (int i = 0; i < adL.getVertices().size(); i++) {
            ways.add(Integer.MAX_VALUE);
            used.add(0);
        }

        ways.set(adL.findId(vert), 0);
        Dijkstra(adL);
    }

    private void Dijkstra(AdjacencyLists<G> adL) {
        Integer v;

        for (int i = 0; i < adL.getVertices().size(); i++) {
            v = null;

            for (int j = 0; j < adL.getVertices().size(); j++) {
                if (used.get(j) == 0 && (v == null || ways.get(j) < ways.get(v))) {
                    v = j;
                }
            }

            if (ways.get(v) == Integer.MAX_VALUE) {
                break;
            }

            used.set(v, 1);
            Vertex<G> vert = adL.getVertices().get(v);
            for (Vertex<G> curr_vert : adL.getVertices()) {
                Edge<G> edge = adL.getEdge(vert.getVert(), curr_vert.getVert());

                if (edge != null && ways.get(v) + (Integer) edge.getWeight() < ways.get(adL.findId(curr_vert.getVert()))) {
                    ways.set(adL.findId(curr_vert.getVert()), ways.get(v) + (Integer) edge.getWeight());
                }
            }
        }
    }

    public Integer getWay(AdjacencyLists<G> adL, G vert2) {
        return ways.get(adL.findId(vert2));
    }
}
