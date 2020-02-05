import java.util.LinkedList;

public class LinkedListDoubleEndQueue<E> {

    private LinkedList<E> innerLinkedList;

    public LinkedListDoubleEndQueue() {
        innerLinkedList = new LinkedList<>();
    }

    public int size() {
        return innerLinkedList.size();
    }

    public boolean isEmpty() {
        return innerLinkedList.isEmpty();
    }

    public void enQueueRear(E element) {
        innerLinkedList.addLast(element);
    }

    public void enQueueFront(E element) {
        innerLinkedList.addFirst(element);
    }

    public E deQueueRear() {
        rangeCheck();
        return innerLinkedList.removeLast();
    }

    public E deQueueFront() {
        rangeCheck();
        return innerLinkedList.removeFirst();
    }

    public E front() {
        rangeCheck();
        return innerLinkedList.peekFirst();
    }

    public E rear() {
        rangeCheck();
        return innerLinkedList.peekLast();
    }

    public void clear() {
        innerLinkedList.clear();
    }

    private void rangeCheck() {
        if (innerLinkedList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int size = innerLinkedList.size();

        sb.append("size = ").append(size);

        sb.append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(innerLinkedList.get(i));
        }
        sb.append("]");

        return sb.toString();
    }
}
