package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.List;

/**
 * Представление графа в виде матрицы смежности.
 */
public class AdjacencyMatrix<G> implements Graph<G> {
    List<ArrayList<Edge<G>>> matrix;
    List<ArrayList<Integer>> warshall;
    ArrayList<Vertex<G>> verticesName;
    int countVert;

    /**
     * On MATRIX(i, j) фыves null or object edge.
     */
    public AdjacencyMatrix() {
        matrix = new ArrayList<ArrayList<Edge<G>>>();
        verticesName = new ArrayList<Vertex<G>>();
        countVert = 0;
        warshall = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Метод для добавления вершины в граф.
     */
    //увеличиваем количество вершин на 1, закидываем её в массив вершин
    public void addVert(G vert1) {
        this.verticesName.add(new Vertex<>(vert1));
        matrix.add(new ArrayList<Edge<G>>());
        //заполняю матрицу нулевыми значениями, чтобы ыбло к чему обращаться
        for (int i = 0; i < this.countVert + 1; i++) {
            this.matrix.get(i).add(new Edge<>());
            this.matrix.get(countVert).add(new Edge<>());
        }
        countVert++;
    }

    /**
     * Метод для удаления вершины из графа.
     */
    public void deleteVert(G vert1) {
        int indexVert1 = 0;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(verticesName.get(i).vert)) {
                indexVert1 = i;
            }
        }
        //удаляю у всех вершин информацию об удаляемой
        for (int i = 0; i < countVert; i++) {
            matrix.get(i).remove(indexVert1);
        }
        //удаляю вершину
        matrix.remove(indexVert1);
        verticesName.remove(indexVert1);
        //уменьшаю количество вершин
        countVert--;
    }

    /**
     * Метод для добавления ребра в граф.
     */
    //возможно edge нужно будет поменять на инт
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
            indexVert1 = this.countVert - 1;
        }
        if (indexVert2 == -1) {
            addVert(vert2);
            indexVert2 = this.countVert - 1;
        }
        matrix.get(indexVert1).set(indexVert2, new Edge<G>(vert1, vert2, newEdge));
    }

    /**
     * Метод для удаления ребра.
     */
    public void deleteEdge(G vert1, G vert2) {
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
        //удаление
        matrix.get(indexVert1).set(indexVert2, null);
    }

    /**
     * Метод для получения объекта ребра.
     */
    //буду искать в матрице это ребро и потом возвращать его
    public Edge<G> getEdge(G vert1, G vert2) {
        //вынести поиск индекса в отдельную функцию, но к жёсткому дедлайну
        int indexVert1 = - 1;
        int indexVert2 = - 1;
        for (int i = 0; i < countVert; i++) {
            if (vert1.equals(this.verticesName.get(i).vert)) {
                indexVert1 = i;
            }
            if (vert2.equals(this.verticesName.get(i).vert)) {
                indexVert2 = i;
            }
        }
        //если ребра не существует
        if (indexVert1 == - 1) {
            return null;
        }
        if (indexVert2 == -1) {
            return null;
        }
        return matrix.get(indexVert1).get(indexVert2);
    }

    /**
     * Зная вершины соединяющие ребро, изменяем его вес.
     */
    //считаю, что изменение ребра это изменение его значения
    public void setEdge(G vert1, G vert2, G newEdge) {
        addEdge(vert1, vert2, newEdge);
    }

    /**
     * Get vertice object if vertice not null.
     */
    //получаю имя вершины, вывожу все инцедентные рёбра и и нцедентные вершины
    //можно чтобы в тестах не сравнивать с налом весь объект создать
    // где-нибудь вершину с нулевыми полями
    public Vertex<G> getVert(G vert) {
        for (int i = 0; i < countVert; i++) {
            if (vert.equals(this.verticesName.get(i).vert)) {
                return this.verticesName.get(i);
            }
        }
        //если такой вершины нет null
        return null;
    }

    /**
     * Изменение названия вершины.
     */
    //считаю, что изменение вершины это изменение её имени
    public void setVert(G oldVert, G newVert) {
        for (int i = 0; i < countVert; i++) {
            if (oldVert.equals(this.verticesName.get(i).vert)) {
                this.verticesName.get(i).vert = newVert;
            }
        }
    }

    /**
     * Алгоритм нахождения кратчайшего пути между вершинами.
     */
    //пока реализую только для интеджеров
    public Integer warshall(G vert1, G vert2) {
        for (int k = 0; k < countVert; k++) {
            warshall.add(new ArrayList<Integer>());
            for (int i = 0; i < countVert; i++) {
                warshall.get(k).add(0);
                if (matrix.get(k).get(i).weight != null) {
                    warshall.get(k).set(i, (Integer) matrix.get(k).get(i).weight);
                }
            }
        }
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
        return warshall.get(indexVert1).get(indexVert2);
    }
}