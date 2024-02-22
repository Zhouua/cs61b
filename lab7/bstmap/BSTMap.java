package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node treeRoot;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node root;
        private Node left;
        private Node right;

        private Node(K k, V v, Node r) {
            key = k;
            value = v;
            root = r;
            left = null;
            right = null;
        }
    }

    public BSTMap() {
        size = 0;
        treeRoot = null;
    }

    @Override
    public void clear() {
        size = 0;
        treeRoot = null;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    private Node getNode(K key) {
        Node p = treeRoot;
        while (p != null) {
            int cmp = p.key.compareTo(key);
            if (cmp > 0) {
                p = p.left;
            } else if (cmp < 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }
    @Override
    public V get(K key) {
        Node p = getNode(key);
        if (p == null) {
            return null;
        }
        return p.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        size += 1;
        if (treeRoot == null) {
            treeRoot = new Node(key, value, null);
            return;
        }
        Node pre = treeRoot;
        Node p = treeRoot;
        // find where to store value
        while (p != null) {
            int cmp = p.key.compareTo(key);
            if (cmp < 0) {
                pre = p;
                p = p.right;
            } else if (cmp > 0) {
                pre = p;
                p = p.left;
            } else {
                System.out.println("Wrong Key Input!");
                return;
            }
        }
        // put value
        Node put = new Node(key, value, pre);
        if (pre.key.compareTo(key) > 0) {
            pre.left = put;
        } else {
            pre.right = put;
        }
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /** prints out BSTMap in order of increasing Key.*/
    private void print(Node p) {
        if (p == null) {
            return;
        }
        if (p.left == null) {
            System.out.println(p.value);
            print(p.right);
        } else if (p.right != null) {
            print(p.left);
            System.out.println(p.value);
            print(p.right);
        }
    }
    public void printInOrder() {
        Node p = treeRoot;
        print(p);
    }
}
