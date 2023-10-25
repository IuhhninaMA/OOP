package ru.nsu.yukhnina;

import java.util.ArrayList;

public class AdjacencyLists<G> implements Graph<G> {
    //будем считать , что список смежности это список рёбер для каждой вершиныб
    //буду хранить массив вершин, чтобы по ним получать индекс необходимого подмассива

    ArrayList<ArrayList<Edge<G>>> edgesName;
    int vertexCount;
    ArrayList<Vertex<G>> vertices;

    /**
     * Object contains matrix edges, matrix nameVert to find vert index and count vert.
     */
    public AdjacencyLists() {
        edgesName = new ArrayList<ArrayList<Edge<G>>>();
        vertexCount = 0;
        vertices = new ArrayList<Vertex<G>>();
    }

    /**
     * Add new vert in graph.
     */
    public void addVert(G newVert) {
        //добавили новую вершину в массив вершин, увеличили количество,
        vertices.add(new Vertex<G>(newVert));
        vertexCount++;
        edgesName.add(new ArrayList<Edge<G>>());
    }

    /**
     * Get vertices object.
     */
    public Vertex<G> getVert(G vert) {
        for (int i = 0; i < vertexCount; i++) {
            if (vert.equals(this.vertices.get(i).vert)) {
                return this.vertices.get(i);
            }
        }
        //если нет такой вершины возвращаю null
        return null;
    }

    /**
     * Change vertice name.
     */
    public void setVert(G oldVert, G newVert) {
        for (int i = 0; i < vertexCount; i++) {
            if (oldVert.equals(this.vertices.get(i).vert)) {
                this.vertices.get(i).vert = newVert;
            }
        }
    }

    /**
     * If we know vert nameб delite it in graph.
     */
    public void deleteVert(G vert) {
        //ищу индекс вершины
        int indexVert = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert.equals(this.vertices.get(i).vert)) {
                indexVert = i;
                break;
            }
        }
        //если такой вершины нет, то удалять нечего
        if (indexVert < 0) {
            return;
        }
        //удаляем всеинцеденты=ные рёбра
        vertices.remove(indexVert);
        edgesName.remove(indexVert);
        this.vertexCount--;
    }

    /**
     * Add edge in graph.
     */
    public void addEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i).vert)) {
                indexVert1 = i;
            }
        }
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.vertexCount;
        }
        edgesName.get(indexVert1).add(new Edge<>(vert1, vert2, newEdge));
        //попроавить удаление вершины!!!!!!!!!!!!!!! сломаться ничего не должно, но всё же
    }

    /**
     * Получение объекта ребра.
     */
    public Edge<G> getEdge(G vert1, G vert2) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i).vert)) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i).vert)) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            return null;
        }
        if (indexVert2 == - 1) {
            return null;
        }
        for (Edge<G> edge : edgesName.get(indexVert1)) {
            if (edge.vertFrom.equals(vert1) & edge.vertTo.equals(vert2)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Method for change edges weight.
     */
    public void setEdge(G vert1, G vert2, G newEdge) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i).vert)) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i).vert)) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            addVert(vert1);
            indexVert1 = this.vertexCount;
        }
        if (indexVert2 == - 1) {
            addVert(vert2);
            indexVert2 = this.vertexCount;
        }
        //если всё таки нашли это ребро
        for (Edge<G> edge : edgesName.get(indexVert1)) {
            if (edge.vertFrom.equals(vert1) & edge.vertTo.equals(vert2)) {
                edge.weight = newEdge;
                return;
            }
        }
        //если не нашли такое ребро, создаём
        addEdge(vert1, vert2, newEdge);
    }

    /**
     * Метод для удаления ребра из графа.
     */
    public void deleteEdge(G vert1, G vert2) {
        int indexVert1 = -1;
        int indexVert2 = -1;
        for (int i = 0; i < vertexCount; i++) {
            if (vert1.equals(this.vertices.get(i))) {
                indexVert1 = i;
            }
            if (vert2.equals(this.vertices.get(i))) {
                indexVert2 = i;
            }
        }
        if (indexVert1 == - 1) {
            return;
        }
        if (indexVert2 == - 1) {
            return;
        }
        for (Edge<G> edge : edgesName.get(indexVert1)) {
            if (edge.vertFrom.equals(vert1) & edge.vertTo.equals(vert2)) {
                edgesName.get(indexVert1).remove(edge);
                return;
            }
        }
    }
}
