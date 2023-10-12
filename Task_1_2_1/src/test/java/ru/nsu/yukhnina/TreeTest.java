package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TreeTest {
    @Test
    void testFromTask() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.remove();
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        BFSIterator<String> iterableImpl = new BFSIterator<>(tree);
        String[] result = {"R1", "A", "R2", "C", "D"};
        int i = 0;
        for (var element : iterableImpl) {
            assertEquals(result[i++], element.getValue());
        }
        i = 0;
        DFSIterator<String> iterableDfs = new DFSIterator<>(subtree);
        for (var element : iterableDfs) {
            assertEquals(result[i++], element.getValue());
        }
        assertEquals(subtree, subtree);
    }

    @Test
    void testCallFromNotRootBfs() {
        Tree<String> tree2 = new Tree<>("R1");
        tree2.addChild("A");
        tree2.addChild("B");
        tree2.addChild("C");
        var d = tree2.addChild("D");
        var e = d.addChild("E");
        String[] result = {"R1", "A", "B", "C", "D", "E"};
        BFSIterator<String> iterableImpl = new BFSIterator<>(e);
        int i = 0;
        for (var element : iterableImpl) {
            assertEquals(result[i++], element.getValue());
        }
    }

    @Test
    void testCallFromNotRootDfs() {
        Tree<String> tree2 = new Tree<>("R1");
        var d = tree2.addChild("D");
        var e = d.addChild("E");
        tree2.addChild("A");
        tree2.addChild("B");
        tree2.addChild("C");
        String[] result = {"R1", "D", "E", "A", "B", "C"};
        DFSIterator<String> iterableImpl = new DFSIterator<>(e);
        int i = 0;
        for (var element : iterableImpl) {
            assertEquals(result[i++], element.getValue());
        }
    }

    @Test
    void testRemoveInt() {
        Tree<Integer> tree3 = new Tree<>(1);
        var d = tree3.addChild(2);
        d.addChild(3);
        d.remove();
        tree3.addChild(4);
        tree3.addChild(5);
        tree3.addChild(6);
        Integer[] result = {1, 4, 5, 6};
        DFSIterator<Integer> iterableImpl = new DFSIterator<Integer>(d);
        int i = 0;
        for (var element : iterableImpl) {
            assertEquals(result[i++], element.getValue());
        }
    }

    @Test
    void testEqualsTreeFalse() {
        Tree<Integer> tree4 = new Tree<>(1);
        var d = tree4.addChild(2);
        tree4.addChild(4);
        tree4.addChild(5);
        tree4.addChild(6);
        d.addChild(3);
        d.remove();
        Tree<Integer> tree41 = new Tree<>(1);
        tree41.addChild(4);
        tree41.addChild(155);
        tree41.addChild(166);
        assertNotEquals(tree4, tree41);
    }

    @Test
    void testEqualsTreeTrue() {
        Tree<Character> tree5 = new Tree<>('p');
        var d = tree5.addChild('q');
        d.addChild('m');
        d.remove();
        tree5.addChild('u');
        tree5.addChild('w');
        tree5.addChild('z');
        Tree<Character> tree51 = new Tree<>('p');
        tree51.addChild('u');
        tree51.addChild('w');
        tree51.addChild('z');
        assertEquals(tree5, tree51);
    }
}