package problemspp;

import linkedlist.Node;

public class RehersalPP {
    /*
        2 - 4 - 3
        5 - 6 - 4
        reverse sum - 708
     */
    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummyNode=new Node(-1);
        Node temp=dummyNode;
        int carry=0;
        while(l1 !=null || l2 !=null){
            int sum=carry;
            if(l1!=null) sum+=l1.value;
            if(l2!=null) sum+=l2.value;
            temp.next=new Node(sum%10);
            carry=sum/10;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
            temp=temp.next;
        }
        if(carry!=0){
            temp.next=new Node(carry);
            temp=temp.next;
            temp.next=null;
        }
        return dummyNode.next;
    }

    /*
    k=2
    1 ----> 2 ----> 3 -----> 4 ------> 5
    2------>1 ----->4 ------> 3 ------>5
     */

    public Node reverseKGroup(Node head, int k){
        if(head==null || head.next==null) return head;
        Node currHead=head;
        Node prevHead=null;
        int len=calculateLength(head);
        int groups=len/k;
        Node ans=null;
        for(int i=0;i<groups;i++){
            Node curr=currHead;
            Node after=null;
            Node prev=null;
            for(int j=0;j<k;j++){
                after=curr.next;
                curr.next=prev;
                prev=curr;
                curr=after;
            }
            if(prevHead==null){
                ans=prev;
            }else{
                prevHead.next=prev;
            }
            prevHead=currHead;
            currHead=curr;
        }
        prevHead.next=currHead;
        return ans;
    }

    private int calculateLength(Node head) {
        int count=0;
        while(head!=null){
            count++;
            head=head.next;
        }

        return count;
    }

    public static void main(String[] args) {

    }
}
