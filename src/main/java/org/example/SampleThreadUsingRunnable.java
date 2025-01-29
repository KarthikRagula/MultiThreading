package org.example;

public class SampleThreadUsingRunnable implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" -> "+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String args[]){
        SampleThreadUsingRunnable t=new SampleThreadUsingRunnable();
        Thread t1=new Thread(t);
        Thread t2=new Thread(t);
        t1.start();
        t2.start();
        System.out.println("Main Thread is Running");
    }
}
