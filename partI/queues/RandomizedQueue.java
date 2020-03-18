import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int n;

    public RandomizedQueue() {
        s = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == s.length) resize(2 * s.length);
        s[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int pos = StdRandom.uniform(n);
        Item item = s[pos];
        for (int i = pos; i < n - 1; i++)
            s[i] = s[i + 1];
        n--;
        s[n] = null;
        if (n > 0 && n == s.length / 4) resize(s.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int pos = StdRandom.uniform(n);
        return s[pos];
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = s[i];
        s = copy;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private final int[] a;

        public RandomizedQueueIterator() {
            a = new int[n];
            for (int c = 0; c < n; c++) {
                a[c] = c;
            }
            StdRandom.shuffle(a);
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[a[i--]];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> deque = new RandomizedQueue<>();
        String item = args[0];
        StdOut.println("String: " + item);
        for (char c : item.toCharArray()) {
            if (c != '-')
                deque.enqueue(Character.toString(c));
            else if (!deque.isEmpty())
                StdOut.print(deque.dequeue() + " ");
        }
        StdOut.println("(" + deque.size() + " left on stack)");
    }
}
