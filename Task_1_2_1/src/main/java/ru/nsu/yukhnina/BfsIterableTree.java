package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Iterator based on breadth-first search algorithm.
 */
public class BfsIterableTree<T> implements Iterable<Tree<T>> {
    private ArrayList<Tree<T>> arrayTree;
    private int currentSize;

    /**
     * called bfs function that create tree`s nodes array.
     *
     * @param node something node from iterable tree.
     */
    public BfsIterableTree(Tree<T> node) {
        this.arrayTree = bfs(node);
        this.currentSize = arrayTree.size();
    }

    /**
     * The breadth-first search algorithm constructs an array of graph vertices.
     *
     * @param node something node from iterable tree.
     * @return array consists of trees node.
     */
    private ArrayList<Tree<T>> bfs(Tree<T> node) {
        Tree<T> root = findFather(node);
        root.setFlagIterator(true);
        ArrayList<Tree<T>> array = new ArrayList<Tree<T>>();
        array.add(root);
        ArrayList<Tree<T>> queue = new ArrayList<Tree<T>>(root.getChildren());
        while (!queue.isEmpty()) {
            array.add(queue.get(0));
            queue.addAll(queue.get(0).getChildren());
            queue.remove(0);
        }
        root.setFlagIterator(false);
        return array;
    }

    /**
     * Find trees`s node.
     *
     * @param node its node from tree where we try to find root.
     * @return this tree root.
     */
    private Tree<T> findFather(Tree<T> node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
        return node;
    }

    /**
     * This functions need in equals to compare trees.
     *
     * @return count of nodes in tree.
     */
    public int len() {
        return arrayTree.size();
    }

    @Override
    public Iterator<Tree<T>> iterator() {
        Iterator<Tree<T>> it = new Iterator<Tree<T>>() {

            private int currentIndex = 0;




            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayTree.get(currentIndex) != null;
            }




            @Override
            public Tree<T> next() {
                return arrayTree.get(currentIndex++);
            }
        };
        return it;
    }
}