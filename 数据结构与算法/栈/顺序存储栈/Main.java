public class Main {

    public static void main(String[] args) {
        SequenceStack<Integer> s = new SequenceStack<>();
        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);
        s.push(50);
        System.out.println(s);
        // size = 5, [10, 20, 30, 40, 50]

        System.out.println("----------");
        System.out.println("s.peek() = " + s.peek());
        // s.peek() = 50
        System.out.println(s);
        // size = 5, [10, 20, 30, 40, 50]

        System.out.println("----------");
        System.out.println("s.pop() = " + s.pop());
        // s.pop() = 50
        System.out.println(s);
        // size = 4, [10, 20, 30, 40]

        System.out.println("----------");
        s.push(60);
        System.out.println(s);
        // size = 5, [10, 20, 30, 40, 60]
        
        System.out.println("----------");
        s.clear();
        System.out.println(s);
        // size = 0, []
    }
}