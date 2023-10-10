package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;

    public class BFSIterator<T> implements Iterable<Tree<T>> {

        private ArrayList<Tree<T>> arrayTree;
        private int currentSize;

        public BFSIterator(Tree<T> node) {
            this.arrayTree = bfs(node);
            this.currentSize = arrayTree.size();
        }

        private ArrayList<Tree<T>> bfs(Tree<T> node) {
            Tree<T> root = findFather(node);
            ArrayList<Tree<T>> array = new ArrayList<Tree<T>>();
            array.add(root);
            ArrayList<Tree<T>> queue = new ArrayList<Tree<T>>(root.getChildren());
            while (!queue.isEmpty()) {
                array.add(queue.get(0));
                queue.addAll(queue.get(0).getChildren());
                queue.remove(0);
            }
            return array;
        }

        private Tree<T> findFather(Tree<T> node) {
            while (node.getParent() != null) {
                node = node.getParent();
            }
            return node;
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