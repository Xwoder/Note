public class Main {

    public static void main(String[] args) {
        LinkedListQueue<Integer> s = new LinkedListQueue<>();
        s.enQueue(10);
        s.enQueue(20);
        s.enQueue(30);
        s.enQueue(40);
        s.enQueue(50);
        System.out.println(s);
        // size = 5, [10, 20, 30, 40, 50]

        System.out.println("----------");
        System.out.println("s.front() = " + s.front());
        // s.front() = 10
        System.out.println(s);
        // size = 5, [10, 20, 30, 40, 50]

        System.out.println("----------");
        System.out.println("s.deQueue() = " + s.deQueue());
        // s.deQueue() = 10
        System.out.println(s);
        // size = 4, [20, 30, 40, 50]

        System.out.println("----------");
        System.out.println("s.deQueue() = " + s.deQueue());
        // s.deQueue() = 20
        System.out.println(s);
        // size = 3, [30, 40, 50]

        System.out.println("----------");
        s.enQueue(60);
        System.out.println(s);
        // size = 4, [30, 40, 50, 60]
        
        System.out.println("----------");
        s.clear();
        System.out.println(s);
        // size = 0, []
    }
}