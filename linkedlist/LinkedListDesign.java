package linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LinkedListDesign {

    Node head;
    Node tail;

    int size;

    public LinkedListDesign(int value) {
        Node newNode=new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.size = 1;
    }

    public void getHead(){
        System.out.println("head is " + head.value);
    }

    public void getTail(){
        System.out.println("tail is " + tail.value);
    }

    public void getSize(){
        System.out.println("length is " + this.size);
    }

    public void display(){
        if(head==null)
            System.out.println("Linked list is empty");
        else{
            Node curr=head;
            while(curr!=null){
                System.out.print(curr.value + " ");
                curr=curr.next;
            }
        }
    }

    public void insertFirst(int value){
        Node node=new Node(value);
        if(head==null){
            head=node;
            tail=node;
            this.size=1;
        }
        else {
            node.next=head;
            head=node;
            this.size+=1;
        }
    }

    public void insertLast(int value){
        if(head==null){
            insertFirst(value);
        }else{
            Node n=new Node(value);
            System.out.println("tail " + tail.value);
            tail.next=n;
            tail=n;
        }
        this.size+=1;
    }

    public void insertInPosition(int value, int pos){
       // System.out.println("size " + this.size);
        if(pos<0 || pos>size){
            System.out.println("Position is not correct");
        }else if(pos==0) {
            insertFirst(value);
            System.out.println("size " + this.size);
            //this.size += 1;
        }
        else if(pos==size) {
            insertLast(value);
            System.out.println("size " + this.size);
            //this.size += 1;
        }
        else{
            Node n=new Node(value);
            Node curr=head;
            Node prev=head;
            for(int i=0;i<pos;i++){
                prev=curr;
                curr=curr.next;
            }
            n.next=curr;
            prev.next=n;
            this.size+=1;
        }

    }

    public void deleteFirst(){
        if(this.size==0)
            System.out.println("linked list has no elements");
       else if(this.size==1) {
            head=null;
            System.out.println(""+null);
            this.size=0;
        }else {
            System.out.println(head.value);
            head=head.next;
            this.size-=1;
        }

    }

    public void deleteLast(){
        if(this.size==1){
            head=null;
            tail=null;
            size=0;
        }
        else{
            Node curr=head;
            Node prev=null;
            while(curr.next!=null){
                prev=curr;
                curr=curr.next;
            }
            prev.next=null;
            tail=prev;
            this.size--;
        }
    }

    public void deleteInPosition(int pos){
        if(pos<0 || pos>size)
            System.out.println("position is incorrect");
        else if(pos==0)
            deleteFirst();
        else if(pos==size)
            deleteLast();
        else{
            Node curr=head;
            Node prev=head;
            for(int i=0;i<pos;i++){
                prev=curr;
                curr=curr.next;
            }
            prev.next=curr.next;
            this.size--;
        }
    }

    public void findNode(int value){
        boolean found=false;
        for(int i=0;i<size;i++){
            Node curr=head;
            if(curr.value==value){
                System.out.println("found node " + curr.value + "at position " + i);
                found=true;
            }
            curr=curr.next;
        }
        if(!found)
            System.out.println("No nodes exist with value " + value);
    }

    public void findNodeAtPosition(int pos){
        Node curr=head;
        if(head==null)
            System.out.println("Linked list is empty");
        else {
            for (int i = 0; i < pos; i++) {
                curr = curr.next;
            }
            System.out.println("element at position " + pos + " is" + curr.value);
        }
    }

    public Node get(int index) {
        if (index < 0 || index >= size) return null;
        Node temp = head;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value){
        if(index<0 || index>=size)
            return false;
        else {
            Node temp=get(index);
            if(temp!=null){
                temp.value=value;
                return true;
            }
            else
                return false;
        }
    }

    public Node reverseLinkedList(){
        Node before=null;
        Node temp=head;
       // Node after;
        head=tail;
        tail=temp;
        while(temp!=null){
           Node after=temp.next;
            temp.next=before;
            before=temp;
            temp=after;
        }
        return before;
    }

    public Node reverseLinkedListRecursive(Node head){
        if(head==null || head.next==null)
            return head;
        Node newHead=reverseLinkedListRecursive(head.next);
        Node headNext=head.next;
        headNext.next=head;
        head.next=null;
        return newHead;
    }

    public Node findMiddleNode(){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public void partitionList(int x){
        Node dummy1=new Node(0);
        Node dummy2=new Node(0);
        Node prev1=dummy1;
        Node prev2=dummy2;
        Node curr=head;
        while(curr!=null){
            if(curr.value<x){
                prev1.next=curr;
                prev1=curr;
            }else{
                prev2.next=curr;
                prev2=curr;
            }
            curr=curr.next;
        }
        prev2.next=null;
        prev1.next=dummy2.next;
        head=dummy1.next;
    }

    public void removeDuplicates(){
        HashSet<Integer> set=new HashSet<>();
        Node curr=head;
        Node prev=head;
        while(curr!=null){
            if(set.contains(curr.value)){
                prev.next=curr.next;
            }else{
                set.add(curr.value);
                prev=curr;
            }

            curr=curr.next;
        }
    }

    public static int binaryToDecimal(String bin){
        int num=0;
        int i=0;
        while(i<bin.length()){
            num=num*2;
            int j=Integer.parseInt(String.valueOf(bin.charAt(i)));
            num+= j;
            i++;
        }
        return num;
    }


}
