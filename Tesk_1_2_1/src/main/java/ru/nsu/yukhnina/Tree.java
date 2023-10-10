package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Stack;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tree<T> {
    private ArrayList<Tree<T>> children;
    private Tree<T> parent;
    private T value;
    private int chCount;

    public Tree(T value){
        this.children = new ArrayList<Tree<T>>();
        this.value = value;
        this.chCount = children.size();
    }

    public int getChCount() {
        return chCount;
    }

    public void setChCount(int chCount) {
        this.chCount = chCount;
    }

    public Tree(T value, Tree<T> parent){
        ArrayList<Tree<T>> children = new ArrayList<Tree<T>>();
        this.value = value;
        this.parent = parent;
    }

    public ArrayList<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tree<T>> children) {
        this.children = children;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    //add leaf
    public Tree<T> addChild(T value){
        Tree<T> child = new Tree<T>(value);
        this.children.add(child);
        return child;
    }

    //add subtree
    public Tree<T> addChild(Tree<T> subtree){
        this.children.add(subtree);
        return subtree;
    }

    public void remove(){
        this.children.clear();
        if (this.parent != null) {
            //удаляем его из родителя, если родитель есть
            for (int i = 0; i < this.parent.chCount; i++) {
                if (this.parent.children.get(i) == this) {
                    this.parent.children.remove(i);
                    return;
                }
            }
        }
        //если я правтльно поняла, то дальше java сама всё подчистит
    }


}








