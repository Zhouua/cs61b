package deque;

public class LinkedListDeque<T> {
    private class Node{
        public T item;
        public Node pre;
        public Node next;
        public Node(Node p, T i, Node n){
            pre = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque(){
        // Unsure sentinel = new Node(sentinel, null, sentnel);
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = other.size();
        for (int i = 0; i < size; i++) {
            addLast((T) other.get(i));
        }

    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        sentinel.next = new Node(sentinel, item, sentinel.next);
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        sentinel.pre.next = new Node(sentinel.pre, item, sentinel);
        sentinel.pre = sentinel.pre.next;
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed,print out a new line.
     */
    public void printDeque(){
        Node p = sentinel.next;
        while(p != sentinel){
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
    public T removeFirst(){
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
    public T removeLast(){
        if (size == 0){
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
    public T get(int index){
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
    private T getRecursive(int index, Node p){
        if (index == 0){
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }
    public T getRecursive(int index){
        if (size == 0) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
}
