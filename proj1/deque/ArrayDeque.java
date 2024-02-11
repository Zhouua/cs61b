package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextfront;
    private int nextback;
    private double R;

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[8];
        size = 0;
        nextfront = 0;
        nextback = 1;
        for (int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));
        }
    }

    /** Creates an empty array deque. */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextfront = 0;
        nextback = 1;
    }

    /** Returns the index in the loop. */
    private int check(int i){
        if (i < 0){
            return i + items.length;
        } else if (i > items.length - 1){
            return i - items.length;
        } else {
            return i;
        }
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T i){
        items[nextfront] = i;
        nextfront = check(nextfront - 1);
        size = size + 1;
        resize();
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T i){
        items[nextback] = i;
        nextback = check(nextback + 1);
        size = size + 1;
        resize();
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the size of the deque. */
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque. */
    @Override
    public void printDeque(){
        for (int i = check(nextfront + 1); i < nextback; i = check(i + 1)){
            if (i == check(nextfront + 1)) System.out.print(items[i]);
            else System.out.print(" "+items[i]);
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst(){
        if (size == 0) return null;
        T item = items[check(nextfront + 1)];
        items[check(nextfront + 1)] = null;
        nextfront = check(nextfront + 1);
        size = size - 1;
        resize();
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast(){
        if (size == 0) return null;
        T item = items[check(nextback - 1)];
        items[check(nextback - 1)] = null;
        nextback = check(nextback - 1);
        size = size - 1;
        resize();
        return item;
    }

    /** Gets the item at the given index. */
    @Override
    public T get(int index){
        for (int i = check(nextfront + 1);; i = check(i + 1)){
            if (index == 0) {
                return items[i];
            }
            index = index - 1;
        }
    }

    /** Resizes the size of the deque. */
    public void resize(){
        R = 1.0 * size / items.length;
        if (nextfront == nextback) {
           // upsize
            T[] array = (T[]) new Object[items.length * 2];
            int j = 0;
            while (j < items.length) {
                array[j] = items[check(nextfront + 1 + j)];
                j = j + 1;
            }
            items = array;
            nextfront = items.length - 1;
            nextback = j - 1;
        } else if (R > 0 && R < 0.25){
            // downsize
            T[] array = (T[]) new Object[items.length / 2];
            int j = 0;
            for (int i = check(nextfront + 1); i < nextback; i = check(i + 1)){
                array[j] = items[i];
                j = j + 1;
            }
            items = array;
            nextfront = items.length - 1;
            nextback = j;
        } else {
            return;
        }
    }

    /** The Deque objects we’ll make are iterable. */
    public Iterator<T> iterator() {
        return new myIterator();
    }
    private class myIterator implements Iterator<T> {
        private int pos = 0;
        public boolean hasNext() {
            return pos < size;
        }
        public T next() {
            T returnvalue = items[pos];
            pos = pos + 1;
            return returnvalue;
        }
    }

    /** Returns the parameter o is equal to the Deque. */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (other.size() != this.size()) return false;
        for (int i = 0; i < this.size(); i += 1) {
            if (other.get(i) != this.get(i)) return false;
        }
        return true;
    }
}
