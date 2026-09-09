package stackpp;

import java.util.*;

public class StackProblems {

    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();

    public void enqueueInQueueUsingStack(int val){
        stack1.push(val);
    }

    public int dequeInQueueUsingStack(){
        if(stack1.isEmpty())
            return -1;
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int val=stack2.pop();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return val;
    }

    public int peekInQueueUsingStack(){
        if(stack1.isEmpty())
            return -1;
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int val=stack2.peek();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return val;
    }

    public static void main(String[] args) {
        StackProblems st=new StackProblems();
        st.enqueueInQueueUsingStack(1);
        st.enqueueInQueueUsingStack(2);
        st.enqueueInQueueUsingStack(3);
        System.out.println(st.peekInQueueUsingStack());
        System.out.println(st.dequeInQueueUsingStack());
        st.enqueueInQueueUsingStack(7);
        System.out.println(st.peekInQueueUsingStack());
    }
}
