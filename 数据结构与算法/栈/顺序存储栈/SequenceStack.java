import java.util.ArrayList;

public class SequenceStack<E> {

    private ArrayList<E> innerArrayList;

    public SequenceStack() {
        innerArrayList = new ArrayList<>();
    }

    public int size() {
        return innerArrayList.size();
    }

    public boolean isEmpty() {
        return innerArrayList.isEmpty();
    }

    public void push(E element) {
        innerArrayList.add(element);
    }

    public E pop() {
        if (innerArrayList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerArrayList.remove(innerArrayList.size() - 1);
    }

    public E peek() {
        if (innerArrayList.isEmpty()) {
            throw new IndexOutOfBoundsException(0);
        }
        return innerArrayList.get(innerArrayList.size() - 1);
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
