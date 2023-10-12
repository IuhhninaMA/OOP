package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

    /**
    * class for creating, compare and remove tree and leafs.
    */
public class Tree<T> {
    public static boolean flagIterator = false;//флаг для выкидывания ошибки
    private ArrayList<Tree<T>> children;
    private Tree<T> parent;
    private T value;

    public Tree(T value) {
        this.children = new ArrayList<Tree<T>>();
        this.value = value;
        Tree<T> parent;
    }

    /**
    * because my fields is private I need get and set functions.
    *
    * @return node`s children.
    */
    public ArrayList<Tree<T>> getChildren() {
        return children;
    }

    /**
    * @return node`s father.
    */
    public Tree<T> getParent() {
        return parent;
    }

    /**
    * @return node`s value.
    */
    public T getValue() {
        return value;
    }

    /**
    * Add leaf in tree.
    *
    * @param value
    * @return new child`s link.
    */
    public Tree<T> addChild(T value) {
        Tree<T> child = new Tree<T>(value);
        this.children.add(child);
        child.parent = this;
        return child;
    }

    /**
     * Add subtree in main tree.
     */
    public Tree<T> addChild(Tree<T> subtree) {
        this.children.add(subtree);
        subtree.parent = this;
        return subtree;
    }

    /**
     * method to remove leaf or subtree from main tree.
     * if user trying to remove element for iterabling tree function throws exception.
     */
    public void remove() {
        if (flagIterator) {
            throw new ConcurrentModificationException("Изменение дерева во время итерации");
        }
        this.children.clear();
        if (this.parent != null) {
            //удаляем его из родителя, если родитель есть
            for (int i = 0; i < this.parent.children.size(); i++) {
                if (this.parent.children.get(i) == this) {
                    this.parent.children.remove(i);
                    return;
                }
            }
        }
    }

    /**
     * Compare 2 tree.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Iterator<Tree<T>> iterable1 = new BFSIterator(this).iterator();
        Iterator<Tree<T>> iterable2 = new BFSIterator((Tree<T>)obj).iterator();
        //сравниваю размеры коллекций, если они не равны, то и деревья не эквивалентны
        if (new BFSIterator(this).len() != new BFSIterator((Tree<T>)obj).len()) {
            return false;
        }
        while (iterable1.hasNext() && iterable2.hasNext()) {
            if (iterable1.next().value != iterable2.next().value) {
                return false;
            }
        }
        return true;
    }
}








