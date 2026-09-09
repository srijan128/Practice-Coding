package StackPractice.stackimpl;

import java.util.EmptyStackException;


//The primary advantage of using an array for implementing a stack is that it's simple and requires no
// additional setup.
//However, the size of the array can limit the stack size, leading to a stack overflow.
public class Stack<T> {

    private Object[] stack;
    private int top;

    public Stack(int size){
        stack=new Object[size];
        top=-1;
    }

    public void push(int item){
        if(top==stack.length-1){
            throw new IndexOutOfBoundsException("Stack is full");
        }
        stack[++top]=item;
    }

    public T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T item=(T) stack[top];
        stack[top--]=null;
        return item;
    }

    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T item=(T) stack[top];
        return item;
    }

    public boolean isEmpty(){
        return top==-1;
    }


}
