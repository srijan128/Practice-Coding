package stackpp;

import java.util.*;

public class StackUsingQueue {

    Queue<Integer> q1=new LinkedList<>();
    Queue<Integer> q2=new LinkedList<>();

    public void pushInStackUsingQueue(int val){
        if(q1.isEmpty()){
            q1.offer(val);
        }
        while(!q1.isEmpty()){
            q2.offer(q1.poll());
        }
        q1.offer(val);
        while(!q2.isEmpty()){
            q1.offer(q2.poll());
        }
    }

    public int popInStackUsingQueue(){
        if(q1.isEmpty())
            return -1;
        return q1.poll();
    }

    public int peekInStackUsingQueue(){
        if(q1.isEmpty())
            return -1;
        return q1.peek();
    }

   /* public int popInStackUsingQueue(){
        if(q1.size()==1){
            int val = q1.poll();
            return val;
        }
        while(!q1.isEmpty()){
            q2.offer(q1.poll());
        }

    } */

    public static ArrayList<Integer> nextLargerElement(int[] arr) {
        // code here
        ArrayList<Integer> ans=new ArrayList<>(arr.length);
        int [] a=new int[arr.length];
        Stack<Integer> s=new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            if(s.isEmpty()){
                a[i]=-1;
            }else{
                if(s.peek()>arr[i]){
                    a[i]=s.peek();
                }else{
                    while(!s.isEmpty() && s.peek()<=arr[i])
                        s.pop();
                    if(s.isEmpty()){
                        a[i]=-1;
                    }else{
                        a[i]=s.peek();
                    }
                }
            }
            s.push(arr[i]);
        }
        for(int j:a){
            ans.add(j);
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] a={2,1,7,11,12,3,6,9};
        System.out.println(nextLargerElement(a));
    }
}
