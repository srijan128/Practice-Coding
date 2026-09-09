package threading;

import java.util.LinkedList;
import java.util.Queue;

public class PracticeProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> buffer=new LinkedList<>();
        int size=5;

        Runnable producer=(()->{
            for(int i=0;i<10;i++){
                synchronized (buffer){
                    if(buffer.size()==size){
                        try {
                            System.out.println("buffer is full, so waiting");
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    buffer.add(i);
                    System.out.println("produced " + i);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    buffer.notifyAll();
                }
            }
        });

        Runnable consumer=(()->{
            for(int i=0;i<10;i++){
                synchronized (buffer){
                    if(buffer.size()==0){
                        try {
                            System.out.println("buffer is empty, so waiting");
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int val=buffer.remove();
                    System.out.println("Consumed " + val);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    buffer.notifyAll();
                }
            }
        });

        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
    }
}
