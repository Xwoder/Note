import java.util.LinkedList;

public class LinkedListStack<E> {

    private LinkedList<E> innerLinkedList;

    public LinkedListStack() {
        innerLinkedList = new java.util.LinkedList<>();
    }

    public int size() {
        return innerLinkedList.size();
    }

    public boolean isEmpty() {
        return innerLinkedList.isEmpty();
    }

    public void push(E element) {
        innerLinkedList.add(element);
    }

    public E pop() {
        if (innerLinkedList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerLinkedList.removeLast();
    }

    public E peek() {
        if (innerLinkedList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerLinkedList.getLast();
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
