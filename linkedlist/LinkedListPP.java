package linkedlist;

import java.util.ArrayList;

public class LinkedListPP {

    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummy=new Node(-1);
        Node curr=dummy;
        int carry = 0;
        while(l1 !=null || l2 != null){
            int sum=carry;
            if(l1!=null) sum+=l1.value;
            if(l2!=null) sum+=l2.value;
            Node newNode=new Node(sum%10);
            carry=sum/10;
            curr.next=newNode;
            curr=curr.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }

        if(carry!=0){
            Node newNode=new Node(carry);
            curr.next=newNode;
        }
        return dummy.next;
    }


    /*
    Problem statement
You are given the 'head' of a singly linked list. Your task is to group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list’s head.

The first node is considered odd, and the second node is even, and so on.

Note:
Keep in mind that reordering is to be done according to the indexes and not the node values.
Also, ensure that the relative order inside both the even and odd groups should remain as it was in the input.

Example:
Input: 'head' -> 1 -> 3 -> 5 -> 7

Output: 'head' -> 1 -> 5 -> 3 -> 7
     */
    Node oddEvenList(Node head) {
        ArrayList<Node> list=new ArrayList<>();
        if(head==null || head.next==null)
            return head;
        Node curr=head;
        while(curr!=null && curr.next!=null){
            list.add(curr);
            curr=curr.next.next;
        }
        if(curr!=null) list.add(curr);
        curr=head.next;
        while(curr!=null && curr.next!=null){
            list.add(curr);
            curr=curr.next.next;
        }
        if(curr!=null) list.add(curr);
        curr=head;
        int i=0;
        while(curr!=null){
            curr=list.get(i);
            i++;
            curr=curr.next;
        }
        return head;
    }


    // optimised solution of above problem

    public Node oddEvenListOptimised(Node head) {
        if(head==null || head.next==null)
            return head;
        else
        {
            Node odd=head;
            Node even=head.next;
            Node evenhead=head.next;
            while(odd.next!=null && even.next!=null)
            {
                odd.next=odd.next.next;
                even.next=even.next.next;
                odd=odd.next;
                even=even.next;
            }
            odd.next=evenhead;
        }
        return head;
    }

    //Segregate even and odd nodes in a Linked List GFG Practice
    // Put all even value nodes before all odd value nodes
    // Use the same linked list. Dont create a separate one

    Node segregate(Node head){
        if(head==null || head.next==null)
            return head;
        Node even=new Node(-1);
        Node odd=new Node(-1);
        Node temp=head;
        Node ans=even;
        Node oddHead=odd;
        while(temp!=null){
            if(temp.value%2==0){
                even.next=temp;
                even=even.next;
            }else{
                odd.next=temp;
                odd=odd.next;
            }
            temp=temp.next;
        }
        even.next=null;
        odd.next=null;
        even.next=oddHead.next;
        return ans.next;
    }


    //234. Palindrome Linked List. Very Important

    public boolean isPalindrome(Node head) {
        if(head==null) return true;
        Node slow=head;
        Node fast=head;
        Node prev=null;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            Node next=slow.next;
            slow.next=prev;
            prev=slow;
            slow=next;
        }
        if(fast!=null){
            slow=slow.next;
        }
        while(slow!=null){
            if(slow.value!=prev.value)
                return false;
            slow=slow.next;
            prev=prev.next;
        }
        return true;
    }


    //find intersection pt of 2 LL
    public Node getIntersectionNode(Node headA, Node headB) {
        Node t1=headA;
        Node t2=headB;
        while(t1!=t2){
            t1 = (t1!=null)?t1.next:headB;
            t2 = (t2!=null)?t2.next:headA;
        }
        return t1;
    }

    // Remove Nth Node From End of List
    public Node removeNthFromEnd(Node head, int n) {
        Node first=head;
        Node second=head;
        Node prev=null;
        while(n>0 && second!=null){
            n--;
            second=second.next;
        }
        // the no of nodes in LL is less than n
        if(n!=0)
            return head;
        // scenario we need to delete the head node as second
        // pointer has reached null
        if(second==null){
            Node temp=head;
            head=head.next;
            temp.next=null;
            return head;
        }

        // deleting nth  node from the end
        while(second !=null){
            second=second.next;
            prev=first;
            first=first.next;
        }
        prev.next=first.next;
        first.next=null;
        return head;
    }


    // Merge two sorted linked list
    public static Node sortTwoLists(Node first, Node second) {
        // Write your code here.
        Node dummy=new Node(-1);
        Node temp=dummy;
        while(first!=null && second!=null){
            if(first.value<=second.value){
                temp.next=first;
                first=first.next;
            }else{
                temp.next=second;
                second=second.next;
            }
            temp=temp.next;
        }
        if(first!=null)
            temp.next=first;
        else
            temp.next=second;
        return dummy.next;
    }

    public RandomNode copyRandomList(RandomNode head) {
        RandomNode temp=head;
        // Creating copies of individual nodes
        while(temp !=null){
            RandomNode newNode=new RandomNode(temp.val);
            newNode.next=temp.next;
            temp.next=newNode;
            temp=newNode.next;
        }
        // cloning the random pointers
        temp=head;
        while(temp!=null){
            if(temp.random !=null){
                temp.next.random=temp.random.next;
            }
            temp=temp.next.next;
        }
        // separate both lists and return cloned list
        temp=head;
        RandomNode original = head;
        RandomNode cloned =head.next;
        RandomNode clonedHead=cloned;
        while(cloned.next !=null){
            original.next=original.next.next;
            cloned.next=cloned.next.next;
            original=original.next;
            cloned=cloned.next;
        }
        original.next=null;
        //cloned.next=null;
        return clonedHead;
    }

    public Node reverseKGroup(Node head, int k) {
        int len=calculateLength(head);
        int groups=len/k;
        Node currHead=head;
        Node ansNode=null;
        Node prevHead=null;
        for(int i=0;i<groups;i++){
            Node curr=currHead;
            Node prev=null;
            Node after=null;
            for(int j=0;j<k;j++){
                after=curr.next;
                curr.next=prev;
                prev=curr;
                curr=after;
            }
            if(prevHead==null){
                ansNode=prev;
            }else {
                prevHead.next=prev;
            }
            prevHead=currHead;
            currHead=curr;
        }
        prevHead.next=currHead;
        return ansNode;
    }

    private int calculateLength(Node head) {
        int counter=0;
        while (head!=null){
            counter++;
            head=head.next;
        }
        return counter;
    }


}
