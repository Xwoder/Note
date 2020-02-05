import java.util.ArrayList;

public class SequenceQueue<E> {

    private ArrayList<E> innerArrayList;

    public SequenceQueue() {
        innerArrayList = new ArrayList<>();
    }

    public int size() {
        return innerArrayList.size();
    }

    public boolean isEmpty() {
        return innerArrayList.isEmpty();
    }

    public void enQueue(E element) {
        innerArrayList.add(element);
    }

    public E deQueue() {
        if (innerArrayList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerArrayList.remove(0);
    }

    public E front() {
        if (innerArrayList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerArrayList.get(0);
    }

    public void clear() {
        innerArrayList.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int size = innerArrayList.size();

        sb.append("size = ").append(size);

        sb.append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(innerArrayList.get(i));
        }
        sb.append("]");

        return sb.toString();
    }
}
