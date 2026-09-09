package linkedlist;

public class LinkedListMain {
    public static void main(String[] args) {
        LinkedListDesign listDesign=new LinkedListDesign(3);
        // listDesign.display();
       // listDesign.insertFirst(2);
       // listDesign.deleteFirst();
       // listDesign.insertFirst(1);
       // System.out.println(listDesign.size);
        listDesign.insertLast(8);
        listDesign.insertLast(5);
       /* listDesign.insertLast(10);
        listDesign.insertLast(2);
        listDesign.insertLast(1); */
        //listDesign.insertInPosition(4,3);
       // listDesign.display();
       // System.out.println();
       // listDesign.partitionList(5);
       /* System.out.println();
        listDesign.deleteInPosition(0);
        listDesign.display(); */
        listDesign.display();
        listDesign.reverseLinkedList();
        System.out.println();
        listDesign.display();
        //System.out.println(LinkedListDesign.binaryToDecimal("1011"));
    }
}
