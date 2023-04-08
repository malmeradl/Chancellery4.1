import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack<T> {
    private final LinkedList<T> stack = new LinkedList<>();
    int size = 0;

    public void push(T t) {
        stack.add(t);
        size++;
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        } else {
            size--;
            return stack.removeLast();

        }
    }

    public void show() {
        for (T i : stack) {
            System.out.println(i);
        }
    }

    public T peek() {
        if (!isEmpty()) {
            return stack.getLast();
        } else {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
