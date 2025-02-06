package org.multithreading.samplethreadprograms;

public class SampleThreadUsingExtends extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" -> "+i);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String args[]){
        SampleThreadUsingExtends t1=new SampleThreadUsingExtends();
        SampleThreadUsingExtends t2 =new SampleThreadUsingExtends();
        t1.start();
        t2.start();
    }
}
