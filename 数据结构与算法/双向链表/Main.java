public class Main {

    public static void main(String[] args) {

        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.add(10, 0);
        System.out.println(doublyLinkedList);
        doublyLinkedList.add(20);
        System.out.println(doublyLinkedList);
        doublyLinkedList.add(30);
        System.out.println(doublyLinkedList);
        doublyLinkedList.add(40);
        System.out.println(doublyLinkedList);
        doublyLinkedList.add(25, 2);
        System.out.println(doublyLinkedList);

        doublyLinkedList.add(15, 1);
        System.out.println(doublyLinkedList);

		System.out.println("elementDeleted = " + doublyLinkedList.remove(1));
		System.gc();
		System.out.println(doublyLinkedList);

		doublyLinkedList.add(20, 1);
        System.out.println(doublyLinkedList);
		doublyLinkedList.add(50);
		System.out.println(doublyLinkedList);

		System.out.println("elementDeleted = " + doublyLinkedList.remove(2));
		System.gc();
		System.out.println(doublyLinkedList);

		int indexOfForty = doublyLinkedList.indexOf(40);
		System.out.println("indexOfForty = " + indexOfForty);

		int indexOfFifty = doublyLinkedList.indexOf(50);
		System.out.println("indexOfFifty = " + indexOfFifty);

		doublyLinkedList.add(null, 2);
		System.out.println(doublyLinkedList);
		int indexOfNull = doublyLinkedList.indexOf(null);
		System.out.println("indexOfNull = " + indexOfNull);

        System.out.println("elementDeleted = " + doublyLinkedList.remove(0));
        System.gc();
        System.out.println(doublyLinkedList);
    }
}
