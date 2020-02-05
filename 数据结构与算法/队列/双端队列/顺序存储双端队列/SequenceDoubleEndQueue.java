import java.util.ArrayList;

public class SequenceDoubleEndQueue<E> {

    private ArrayList<E> innerArrayList;

    public SequenceDoubleEndQueue() {
        innerArrayList = new ArrayList<>();
    }

    public int size() {
        return innerArrayList.size();
    }

    public boolean isEmpty() {
        return innerArrayList.isEmpty();
    }

    public void enQueueRear(E element) {
        innerArrayList.add(size(), element);
    }

    public void enQueueFront(E element) {
        innerArrayList.add(0, element);
    }

    public E deQueueRear() {
        rangeCheck();
        return innerArrayList.remove(size() - 1);
    }

    public E deQueueFront() {
        rangeCheck();
        return innerArrayList.remove(0);
    }

    public E front() {
        rangeCheck();
        return innerArrayList.get(0);
    }

    public E rear() {
        rangeCheck();
        return innerArrayList.get(size() - 1);
    }

    public void clear() {
        innerArrayList.clear();
    }

    private void rangeCheck() {
        if (innerArrayList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
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
