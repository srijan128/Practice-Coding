package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintNumberAndLetterAlternate {
    public static volatile boolean isLetter=true;
    //Executors

    public static Object lock=new Object();
    public static void main(String[] args) {
      /* Interview asked
      char c1='a';
        System.out.println(c1+'0');
        System.out.println(Character.getNumericValue('1'));
        int a= (char) '1';
        System.out.println(a);
        System.out.println(c1+(char) 1-'0');
        System.out.println(""); */
        Runnable letterTask=(()->{
            for(char c='A';c<='Z';c++){
                synchronized (lock){
                    if(!isLetter){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(" " + c);
                    isLetter=false;
                    lock.notify();
                }
            }
        });

        Runnable numberTask=(()->{
            for(int i=1;i<=26;i++){
                synchronized (lock){
                    try {
                        if (isLetter) {
                            lock.wait();
                        }
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }

                    System.out.print(" " + i);
                    isLetter=true;
                    lock.notify();
                }
            }
        });

        Thread letterThread=new Thread(letterTask);
        Thread numberThread=new Thread(numberTask);
        letterThread.start();
        numberThread.start();
        try {
            letterThread.join();
            numberThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
