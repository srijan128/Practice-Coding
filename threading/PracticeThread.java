package threading;

import java.util.ArrayList;
import java.util.List;

public class PracticeThread {

    private volatile static boolean isletter=true;

    private static final Object lock=new Object();



    public static void main(String[] args) throws InterruptedException {
        Runnable letterTask=new Runnable() {
            @Override
            public void run() {

                for(char ch ='A';ch<='Z';ch++){
                    synchronized (lock){
                        if(!isletter) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(" " + ch);
                        isletter=false;
                        lock.notifyAll();
                    }

                }
            }
        };

        Runnable numberTask=new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=26;i++){
                    synchronized (lock){
                        if(isletter){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(" " + i);
                        isletter=true;
                        lock.notifyAll();
                    }
                }
            }
        };

        Thread t1=new Thread(letterTask);
        Thread t2=new Thread(numberTask);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
