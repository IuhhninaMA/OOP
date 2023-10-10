package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    void test1() {

        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();
    }
}