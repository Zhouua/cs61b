package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Comparable<MaxArrayDeque<T>> {
    private Comparator<T> comparator;
    /** Creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /** Compares the size of the deque.
     * If this.size > o.size, return positive number, and so forth.
     */
    public int compareTo(MaxArrayDeque<T> o) {
        return this.size() - o.size();
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null. */
    public T max() {
        return max(comparator);
    }

    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        int maxDex = 0;
        for (int i = 0; i < this.size(); i += 1) {
            if (c.compare(get(i), get(maxDex)) > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }
}
