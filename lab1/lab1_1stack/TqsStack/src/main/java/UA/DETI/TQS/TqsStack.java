package UA.DETI.TQS;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {

    LinkedList<T> collection = new LinkedList<T>();
    private int sizeMax = 0;

    public TqsStack() {
        collection = new LinkedList<T>();
    }

    public TqsStack(int sizeMax) {
        collection = new LinkedList<T>();
        this.sizeMax = sizeMax;
    }

    public void push(T element) {
        if (sizeMax > 0 && collection.size() == this.sizeMax) {
            throw new IllegalStateException("Warning: Stack is full!");
        }
        collection.addFirst(element);
    }

    public T pop() {
        if (!collection.isEmpty()) {
            return collection.removeFirst();
        } else {
            throw new NoSuchElementException("Warning: Stack is empty!");
        }
    }

    public T peek() {
        if (collection.isEmpty()) {
            throw new NoSuchElementException("Warning: Stack is empty!");
        }
        return collection.getFirst();
    }

    public int size() {
        return collection.size();
        // return 0;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
}