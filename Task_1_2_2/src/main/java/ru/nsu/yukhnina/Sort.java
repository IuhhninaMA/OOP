package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.List;

public class Sort<G>{
    AdjacencyLists<G> adL;
    List<ArrayList<Integer>> warshall;

    public Sort(AdjacencyLists<G> graph){
        adL = graph;
        warshall = adL.prepareToSort();
    }

    Integer countVert = warshall.size();
    private void floydWarshall() {
        for (int k = 0; k < countVert; k++) {
            for (int i = 0; i < countVert; i++) {
                for (int j = 0; j < countVert; j++) {
                    if ((warshall.get(i).get(j) > warshall.get(i).get(k) + warshall.get(k).get(j))
                            & (warshall.get(i).get(k) + warshall.get(k).get(j) > 0)) {
                        warshall.get(i).set(j, warshall.get(i).get(k) + warshall.get(k).get(j));
                    }
                }
            }
        }
    }
    public Integer findWay(G vert1, G vert2){
        floydWarshall();
        int indexVert1 = adL.findId(vert1);
        int indexVert2 = adL.findId(vert2);
        if (indexVert1 < 0) {
            return 0;
        }
        if (indexVert2 < 0) {
            return 0;
        }
        return this.warshall.get(indexVert1).get(indexVert2);
    }

}
