import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    public boolean isEmpty() {
        return (n == 0);
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (isEmpty()) last = first;
        else oldfirst.previous = first;
        n++;
        assert check();
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
        assert check();
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else
            first.previous = null;
        assert check();
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else
            last.next = null;
        assert check();
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        }
        else if (n == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null) return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        String item = args[0];
        StdOut.println("String: " + item);
        for (char c : item.toCharArray()) {
            if (c != '-')
                deque.addFirst(Character.toString(c));
            else if (!deque.isEmpty())
                StdOut.print(deque.removeFirst() + " ");
        }
        StdOut.println("(" + deque.size() + " left on stack)");
    }
}
