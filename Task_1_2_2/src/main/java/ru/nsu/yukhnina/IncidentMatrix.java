package ru.nsu.yukhnina;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Одно из представлений графа - матрица инцедентности, хранит 1, 0, -1,
 * массив рёбе, массив вершин, количество вершин и рёбер.
 */
public class IncidentMatrix<G> implements Graph<G> {
    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<G> edges;
    ArrayList<Vertex<G>> verticesName;
    ArrayList<Edge<G>> edgesName;
    int countVert;
    int countEdge;

    /**
     * у этого класса есть матрица, хранящая на месте (вершина исходяща, вершина входа)
     * 1 если ребро есть,
     * 0 если ребра нет,
     * -1 на месте (вершина входа, вершина исхода),
     * массив имён вершин для поиска индекса матрицы по мнему,
     * количество вершин и рёбер.
     */
    public IncidentMatrix() {
        matrix = new ArrayList<ArrayList<Integer>>();
        verticesName = new ArrayList<Vertex<G>>();
        edgesName = new ArrayList<Edge<G>>();
        countVert = 0;
        countEdge = 0;
    }

    /**
     * Add vertices in graph.
     */
    public void addVert(G newVert) {
        Vertex<G> newVertex = new Vertex<G>(newVert);
        this.verticesName.add(newVertex);
        matrix.add(new ArrayList<Integer>());
        //заполняю новую строку матрицы нулевыми значениями
        this.countVert++;
        for (int i = 0; i < this.countVert; i++) {
            this.matrix.get(countVert - 1).add(0);
            this.matrix.get(i).add(0);
        }
    }

    /**
     * Get verisces obgect if we know name.
     */
    public Vertex getVert(G vert1) {
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(verticesName.get(i).vert)) {
                return verticesName.get(i);
            }
        }
        return null;
    }

    /**
     * Delete vert from graph.
     */
    public void deleteVert(G newVert) {
        //jgверить, что нигде не остаётся информации об этой вершине
        for (int i = 0; i < countVert; i++) {
            if (newVert.equals(verticesName.get(i).vert)) {
                verticesName.remove(i);
                for (int j = 0; j < countVert; j++) {
                    matrix.get(j).set(i, 0);
                    matrix.get(i).set(j, 0);
                }
                matrix.remove(i);
                countVert--;
                return;
            }
        }

    }

    /**
     * chande vert name.
     */
    public void setVert(G oldVert, G newVert) {
        for (int i = 0; i < countVert; i++) {
            if (oldVert.equals(verticesName.get(i).vert)) {
                verticesName.get(i).vert = newVert;
            }
        }
    }

    /**
     * Add edge in graph.
     */
    public void addEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).vert)) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).vert)) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.countVert;
        }
        if (indexVert2 == -1) {
            addVert(vert2);
            indexVert2 = this.countVert;
        }
        edgesName.add(new Edge<>(vert1, vert2, newEdge));
        matrix.get(indexVert1).set(indexVert2, 1);
        matrix.get(indexVert2).set(indexVert1, -1);
        this.countEdge++;
    }

    /**
     * Remove vertice from graph.
     */
    public void deleteEdge(G vert1, G vert2) {
        //удаляем ребро из массива рёбер
        for (int i = 0; i < this.countEdge; i++) {
            if (vert1.equals(edgesName.get(i).vertFrom) & vert2.equals(edgesName.get(i).vertTo)) {
                edgesName.remove(i);
            }
        }
        //удалить ребро из каждой вершины
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).vert)) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).vert)) {
                indexVert2 = i;
            }
        }
        //если ребра не существует, то и удалять нечего
        if (indexVert1 == - 1) {
            return;
        }
        if (indexVert2 == -1) {
            return;
        }
        matrix.get(indexVert1).set(indexVert2, 0);
        matrix.get(indexVert2).set(indexVert1, 0);
    }

    /**
     * Get edge object if we know vertices from adn to.
     */
    public Edge getEdge(G vert1, G vert2) {
        for (Edge<G> edge : edgesName) {
            if (vert1.equals(edge.vertFrom) & vert2.equals(edge.vertTo)) {
                return edge;
            }
        }
        //если такого ребра не существует
        return null;
    }

    /**
     * Change edge name.
     */
    public void setEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).vert)) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).vert)) {
                indexVert2 = i;
            }
        }
        //сделаю сее, который если нет ребра создаёт новое
        if (indexVert1 == -1) {
            addVert(vert1);
            indexVert1 = this.countVert - 1;
        }
        if (indexVert2 == -1) {
            addVert(vert2);
            indexVert2 = this.countVert - 1;
        }
        if (matrix.get(indexVert1).get(indexVert2) == 0) {
            addEdge(vert1, vert2, newEdge);
            return;
        }
        //если такое ребро уже есть, нахожу его и изменяю
        for (int i = 0; i < countEdge; i++) {
            if (vert1.equals(edgesName.get(i).vertFrom) & vert2.equals(edgesName.get(i).vertTo)) {
                edgesName.get(i).weight = newEdge;
            }
        }
    }
}