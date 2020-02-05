public class DoublyLinkedList<E> {

    /**
     * 查找失败
     */
    private static final int ELEMENT_NOT_FIND = -1;

    /**
     * 长度
     */
    private int size = 0;

    private Node<E> first;
    private Node<E> last;

    /**
     * 双向链表节点类
     * @param <E> 节点数据类型
     */
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void finalize() throws Throwable {
            System.out.println("SingleLinkedList.finalize(), element = " + element);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(prev != null ? prev.element : "null");
            sb.append("<-" + element +"->");
            sb.append(next != null ? next.element : "null");
            return sb.toString();
        }
    }

    /**
     * 获取长度
     *
     * @return 长度
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取元素
     *
     * @param index 索引
     * @return 值
     */
    public E get(int index) {
        Node<E> node = node(index);
        return node.element;
    }

    /**
     * 设置元素
     *
     * @param index 索引
     * @param value 新值
     * @return 旧值
     */
    public E set(int index, E value) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = value;
        return old;
    }

    /**
     * 查找元素
     *
     * @param element 元素值
     * @return 下标
     * @throws Exception 未找到异常
     */
    public int indexOf(E element) {

        if (element == null) {
            Node<E> curNode = first;
            for (int i = 0; i < size; i++) {
                if (curNode.element == null) {
                    return i;
                }
                curNode = curNode.next;
            }
        } else {
            Node<E> curNode = first;
            for (int i = 0; i < size; i++) {
                if (curNode.element.equals(element)) {
                    return i;
                }
                curNode = curNode.next;

            }
        }

        return ELEMENT_NOT_FIND;
    }

    /**
     * 是否包含元素
     *
     * @param elements 元素
     * @return 是否包含
     */
    public boolean contains(E elements) {
        return indexOf(elements) != ELEMENT_NOT_FIND;
    }

    /**
     * 清空元素
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 添加元素
     *
     * @param element 元素
     * @return 是否添加成功
     */
    public boolean add(E element) {
        boolean result = add(element, size);
        return result;
    }

    public boolean add(E element, int index) {
        rangeCheckForAdd(index);

        if (index == size) {
            Node<E> newNode = new Node<>(element, last, null);
            if (last == null) {
                first = newNode;
            } else {
                last.next = newNode;
            }
            last = newNode;
        } else {
            Node<E> node = node(index);
            Node<E> prev = node.prev;
            Node<E> newNode = new Node<>(element, prev, node);
            node.prev = newNode;
            if (prev == null) {
                first = newNode;
            } else {
                newNode.prev.next = newNode;
            }
        }

        ++size;

        return true;
    }

    /**
     * 删除元素
     *
     * @param index 索引
     * @return 被删除的元素
     */
    public E remove(int index) {

        rangeCheckForRemove(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }

        --size;
        return node.element;
    }

    public Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = null;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    public void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private void rangeCheckForRemove(int index) {
        rangeCheck(index);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("size = ").append(size);
        sb.append(", [");
        Node<E> curNode = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(curNode);
            curNode = curNode.next;
        }
        sb.append("]");

        return sb.toString();
    }
}