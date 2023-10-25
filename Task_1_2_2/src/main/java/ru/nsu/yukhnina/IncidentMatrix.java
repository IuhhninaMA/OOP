package ru.nsu.yukhnina;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class IncidentMatrix<G> implements Graph<G> {
    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<G> edges;
    ArrayList<Vertex<G>> verticesName;
    ArrayList<Edge<G>> edgesName;
    int countVert;
    int countEdge;

    public IncidentMatrix() {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        verticesName = new ArrayList<Vertex<G>>();
        edgesName = new ArrayList<Edge<G>>();
        countVert = 0;
        countEdge = 0;
    }

    public void addVert(G newVert) {
        this.countVert++;
        Vertex<G> newVertex = new Vertex<G>(newVert);
        this.verticesName.add(newVertex);
        matrix.add(new ArrayList<Integer>());
        //заполняю новую строку матрицы нулевыми значениями
        for (int i = 0; i < this.countVert; i++) {
            this.matrix.get(countVert).add(0);
        }
    }

    public Vertex getVert(G vert1){
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(verticesName.get(i).vert)) {
                return verticesName.get(i);
            }
        }
        return null;
    }

    public void deleteVert(G newVert) {
        //jgверить, что нигде не остаётся информации об этой вершине
        for (int i = 0; i < countVert; i++) {
            if (newVert.equals(verticesName.get(i).vert)) {
                verticesName.remove(i);
                //добавить удаление из матрицы добавить
            }
        }
        countVert--;
    }

    public void setVert(G oldVert, G newVert) {
        for (int i = 0; i < countVert; i++) {
            if (oldVert.equals(verticesName.get(i).vert)) {
                verticesName.get(i).vert = newVert;
            }
        }
    }

    public void addEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1, indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i))) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i))) {
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

    public void deleteEdge(G vert1, G vert2) {
        //удаляем ребро из массива рёбер
        for (int i = 0; i < this.countEdge; i++) {
            if (vert1.equals(edgesName.get(i).vertFrom) & vert2.equals(edgesName.get(i).vertTo)) {
                edgesName.remove(i);
            }
        }
        //удалить ребро из каждой вершины
        int indexVert1 = -1, indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i))) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i))) {
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
        matrix.get(indexVert1).set(indexVert2, null);
        matrix.get(indexVert2).set(indexVert1, null);
    }

    public Edge getEdge(G vert1, G vert2){
        int indexVert1 = -1, indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i))) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i))) {
                indexVert2 = i;
            }
        }
        //если хотя бы одной из вершин нет, то и ребра нет
        if ((indexVert1 == -1) | (indexVert2 == -1)) {
            return null;
        }
        for (int i = 0; i < this.countEdge; i++) {
            if(matrix.get(indexVert1).get(i) == 1 && matrix.get(indexVert2).get(i) == -1) {
                return this.edgesName.get(i);
            }
        }
        //если такого ребра не существует
        return null;
    }

    public void setEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1, indexVert2 = -1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i))) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i))) {
                indexVert2 = i;
            }
        }
        //сделаю сее, который если нет ребра создаёт новое
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.countVert;
        }
        if (indexVert2 == -1) {
            addVert(vert2);
            indexVert2 = this.countVert;
        }
        if (matrix.get(indexVert1).get(indexVert2) == null) {
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