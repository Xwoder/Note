# [LeetCode] 641. Design Circular Deque

[TOC]

## 链接

[641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/)

## 题目

Design your implementation of the circular double-ended queue (deque).

Your implementation should support following operations:

* `MyCircularDeque(k)`: Constructor, set the size of the deque to be k.
* `insertFront()`: Adds an item at the front of Deque. Return true if the operation is successful.
* `insertLast()`: Adds an item at the rear of Deque. Return true if the operation is successful.
* `deleteFront()`: Deletes an item from the front of Deque. Return true if the operation is successful.
* `deleteLast()`: Deletes an item from the rear of Deque. Return true if the operation is successful.
* `getFront()`: Gets the front item from the Deque. If the deque is empty, return -1.
* `getRear()`: Gets the last item from Deque. If the deque is empty, return -1.
* `isEmpty()`: Checks whether Deque is empty or not. 
* `isFull()`: Checks whether Deque is full or not.

**Example:**

```text
MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);                            // return true
circularDeque.insertLast(2);                            // return true
circularDeque.insertFront(3);                           // return true
circularDeque.insertFront(4);                           // return false, the queue is full
circularDeque.getRear();                                // return 2
circularDeque.isFull();                                 // return true
circularDeque.deleteLast();                             // return true
circularDeque.insertFront(4);                           // return true
circularDeque.getFront();                               // return 4
```

**Note:**

* All values will be in the range of [0, 1000].
* The number of operations will be in the range of [1, 1000].
* Please do not use the built-in Deque library.

## 代码

```Java
class MyCircularDeque {

    private int[] array;

    private int capacity;
    private int size;

    private int front;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        capacity = k;
        array = new int[capacity];
        size = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        int newFrontIndex = index(-1);
        array[newFrontIndex] = value;
        front = newFrontIndex;

        ++size;

        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        array[index(size)] = value;

        ++size;

        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        int val = array[index(0)];
        array[index(0)] = 0;
        front = index(1);

        --size;

        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        int val = array[index(size - 1)];
        array[index(size - 1)] = 0;

        --size;

        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        int val = array[front];
        return val;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        int val = array[index(size - 1)];
        return val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == capacity;
    }

    private int index(int offset) {
        if (offset < 0) {
            offset += capacity;
        }

        return (front + offset) % capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
```

