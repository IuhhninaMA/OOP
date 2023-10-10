package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;

public class DFSIterator<T> implements Iterable<Tree<T>> {

    private ArrayList<Tree<T>> arrayTree;
    private int currentSize;

    public DFSIterator(Tree<T> node) {
        this.arrayTree = dfs(node);
        this.currentSize = arrayTree.size();
    }

    private ArrayList<Tree<T>> dfs(Tree<T> node) {
        findFather(node);
        ArrayList<Tree<T>> result = new ArrayList<Tree<T>>(node.getChildren());

        return arrayTree;
    }

    private void dfsRecursive(Tree<T> node) {
        arrayTree.add(node);
        for (int i = 0; i < node.getChCount() - 1; i++) {
            dfsRecursive(node.getChildren().get(i));
        }
    }

    private void findFather(Tree<T> node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
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