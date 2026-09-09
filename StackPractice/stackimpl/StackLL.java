package StackPractice.stackimpl;

import java.util.EmptyStackException;

public class StackLL<T> {

    private static class Node<T>{

        private T value;
        private Node<T> next;
        private Node(T value){
            this.value=value;
        }
    }

    private Node<T> top;

    public void push(T item){
        Node<T> t=new Node<>(item);
        t.next=top;
        top=t;
    }

    public T pop(){
        if(top==null){
            throw  new EmptyStackException();
        }
        Node<T> temp=top;
        top=top.next;
        temp.next=null;
        return temp.value;

    }
}
