import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testEnqueue() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals((Integer)1, queue.peek());
    }

    @Test
    public void testDequeue() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals((Integer)1, queue.dequeue());
        assertEquals((Integer)2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeek() {
        Queue<Integer> queue = new Queue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals((Integer)1, queue.peek());
        assertEquals((Integer)1, queue.peek());
        queue.dequeue();
        assertEquals((Integer)2, queue.peek());
    }

    @Test
    public void testIsEmpty() {
        Queue<Integer> queue = new Queue<>(5);
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

}
