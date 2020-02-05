public class Main {

    public static void main(String[] args) {
        SequenceDoubleEndQueue<Integer> s = new SequenceDoubleEndQueue<>();
        s.enQueueFront(10);
        s.enQueueFront(20);
        s.enQueueRear(30);
        s.enQueueRear(40);
        System.out.println(s);
        // size = 4, [20, 10, 30, 40]

        System.out.println("----------");
        System.out.println("s.front() = " + s.front());
        // s.front() = 20
        System.out.println("s.rear() = " + s.rear());
        // s.rear() = 40

        System.out.println("----------");
        System.out.println("s.deQueueFront() = " + s.deQueueFront());
        // s.deQueueFront() = 20
        System.out.println(s);
        // size = 3, [10, 30, 40]

        System.out.println("----------");
        System.out.println("s.deQueueRear() = " + s.deQueueRear());
        // s.deQueueRear() = 40
        System.out.println(s);
        // size = 2, [10, 30]
    }
}