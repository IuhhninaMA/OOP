package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        TreeIteratorDfs iterator1 = new TreeIteratorDfs<>(tree);
        String[] result = {"R1", "R2", "D", "C", "A"};
        int i = 0;
        while (iterator1.hasNext()) {
            assertEquals(result[i++], iterator1.next().getValue());
        }
    }


    @Test
    void testCallFromNotRootBfs() {
        Tree<String> tree2 = new Tree<>("R1");
        tree2.addChild("A");
        tree2.addChild("B");
        tree2.addChild("C");
        var d = tree2.addChild("D");
        d.addChild("E");
        String[] result = {"R1", "A", "B", "C", "D", "E"};
        TreeIteratorBfs iterator = new TreeIteratorBfs<>(tree2);
        int i = 0;
        while (iterator.hasNext()) {
            Tree element = iterator.next();
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
        String[] result = {"R1", "C", "B", "A", "D", "E"};
        TreeIteratorDfs<String> iterator = new TreeIteratorDfs<>(e);
        int i = 0;
        while (iterator.hasNext()) {
            Tree element = ((TreeIteratorDfs) iterator).next();
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
        Integer[] result = {1, 6, 5, 4};
        TreeIteratorDfs<Integer> iterator = new TreeIteratorDfs<>(tree3);
        int i = 0;
        while (iterator.hasNext()) {
            Tree<Integer> element = iterator.next();
            assertEquals(result[i++], element.getValue());
        }
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
