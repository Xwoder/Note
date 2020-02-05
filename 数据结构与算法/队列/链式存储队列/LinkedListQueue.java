import java.util.LinkedList;

public class LinkedListQueue<E> {

    private LinkedList<E> innerLinkedList;

    public LinkedListQueue() {
        innerLinkedList = new LinkedList<>();
    }

    public int size() {
        return innerLinkedList.size();
    }

    public boolean isEmpty() {
        return innerLinkedList.isEmpty();
    }

    public void enQueue(E element) {
        innerLinkedList.addLast(element);
    }

    public E deQueue() {
        if (innerLinkedList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerLinkedList.removeFirst();
    }

    public E front() {
        if (innerLinkedList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerLinkedList.getFirst();
    }

    public void clear() {
        innerLinkedList.clear();
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
