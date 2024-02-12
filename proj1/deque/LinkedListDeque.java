package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node{
        private T item;
        private Node pre;
        private Node next;
        private Node(Node p, T i, Node n) {
            pre = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        // Unsure sentinel = new Node(sentinel, null, sentnel);
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        sentinel.pre.next = new Node(sentinel.pre, item, sentinel);
        sentinel.pre = sentinel.pre.next;
        size = size + 1;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed,print out a new line.
     */
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.next == sentinel) {
                System.out.print(p.item);
            } else {
                System.out.print(p.item + " ");
            }
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
           T ritem = sentinel.next.item;
           sentinel.next = sentinel.next.next;
           sentinel.next.pre = sentinel;
           size = size - 1;
           return ritem;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T ritem = sentinel.pre.item;
            sentinel.pre.pre.next = sentinel;
            sentinel.pre = sentinel.pre.pre;
            size = size - 1;
            return ritem;
        }
    }

    /** Uses iteration to get the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (size == 0){
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index = index - 1;
        }
        return p.item;
    }

    /** Uses recursion to get the item at the given index. */
    private T getRecursive(int index, Node p) {
        if (index == 0){
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }
    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    /** The Deque objects we’ll make are iterable. */
    public Iterator<T> iterator() {
        return new myIterator();
    }

    private class myIterator implements Iterator<T> {
        private Node pos;
        public myIterator() {
            pos = sentinel.next;
        }
        public T next() {
            T returnnext = pos.item;
            pos = pos.next;
            return returnnext;
        }
        public boolean hasNext() {
            return pos.next != sentinel;
        }
    }

    /** Returns the parameter o is equal to the Deque. */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        LinkedListDeque<T> copy = new LinkedListDeque<>(this);
        for (int i = 0; i < this.size(); i += 1) {
            if (other.removeFirst() != copy.removeFirst()) {
                return false;
            }
        }
        return true;
    }
}
