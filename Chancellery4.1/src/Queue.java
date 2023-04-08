import java.util.NoSuchElementException;

public class Queue<T> {

    private final T[] array;
    int first;
    int last;
    int size;


    public Queue(int capacity) {
        array = (T[]) new Object[capacity];
        first = 0;
        last = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }


    public void enqueue(T t) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        last = (last + 1) % array.length;
        array[last] = t;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = array[first];
        first = (first + 1) % array.length;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return array[first];
    }

}
