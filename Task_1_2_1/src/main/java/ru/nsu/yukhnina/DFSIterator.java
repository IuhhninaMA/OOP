package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;

    /**
    * Iterator based on deep-first search algorithm.
    *
    * @param <T>
    */
public class DFSIterator<T> implements Iterable<Tree<T>> {

    private ArrayList<Tree<T>> arrayTree;
    private int currentSize;

    /**
    * Called dfs function that create array list with tree nodes.
    *
    * @param node
    */
    public DFSIterator(Tree<T> node) {
        this.arrayTree = dfs(node);
        this.currentSize = arrayTree.size();
    }

    /**
    * Find root node of this tree, create result array and call recursive dfs.
    *
    * @param node
    * @return
    */
    private ArrayList<Tree<T>> dfs(Tree<T> node) {
        Tree<T> root = findFather(node);
        ArrayList<Tree<T>> result = new ArrayList<Tree<T>>();
        arrayTree = dfsRecursive(result, root);
        return arrayTree;
    }


    /**
    * Recursively traverses the graph depth first.
    *
    * @param result
    * @param node
    * @return
    */
    private ArrayList<Tree<T>> dfsRecursive(ArrayList<Tree<T>> result, Tree<T> node) {
        result.add(node);
        for (int i = 0; i < node.getChildren().size(); i++) {
            result = dfsRecursive(result, node.getChildren().get(i));
        }
        return result;
    }

    /**
    * Find trees`s node.
    *
    * @param node
    * @return
    */
    private Tree<T> findFather(Tree<T> node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
        return node;
    }

    /**
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