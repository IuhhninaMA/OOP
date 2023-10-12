package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;

/**
* Iterator based on deep-first search algorithm.
*/
public class DfsIterator<T> implements Iterable<Tree<T>> {

    private ArrayList<Tree<T>> arrayTree;
    private int currentSize;

    /**
    * Called dfs function that create array list with tree nodes.
    */
    public DfsIterator(Tree<T> node) {
        this.arrayTree = dfs(node);
        this.currentSize = arrayTree.size();
    }

    /**
    * Find root node of this tree, create result array and call recursive dfs.
    */
    private ArrayList<Tree<T>> dfs(Tree<T> node) {
        Tree<T> root = findFather(node);
        ArrayList<Tree<T>> result = new ArrayList<Tree<T>>();
        arrayTree = dfsRecursive(result, root);
        return arrayTree;
    }


    /**
    * Recursively traverses the graph depth first.
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
    */
    private Tree<T> findFather(Tree<T> node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
        return node;
    }

    /**
    * Iterator I don`t know all.
    */
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