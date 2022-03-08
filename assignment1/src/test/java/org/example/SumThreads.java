package org.example;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class SumThreads  {

    public static void main (String[]args){
        long startTime = System.nanoTime(); // Computation start time
        int sum=0,from=0,to,num;
        num=(int)Math.pow(2,32);
        to=num/9;

        MyThread[] threads = new MyThread[10]; // create an array of threads
        for (int i = 0; i < 10; i++) {


            // create threads
            threads[i] = new MyThread(from,to);
            from = to + 1;
            if(i!=9) {

                to += num / 9;
            }
            else{
                to=(num);
            }
        }

        for (MyThread thread : threads) {
            thread.start(); // start the threads
        }




                for (MyThread thread : threads) {
            try {
                thread.join(); // wait for the threads to terminate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (MyThread thread : threads) {
           sum+=thread.getSum(); // start the threads
        }
        System.out.println(sum);

        long difference = System.nanoTime() - startTime;
        // Print it out
        long minutesInDifference = TimeUnit.NANOSECONDS.toMinutes(difference);
        long secondsInDifference =
                TimeUnit.NANOSECONDS.toSeconds(difference) - TimeUnit.MINUTES.toSeconds(minutesInDifference);
        long milisecondsInDifference =
                TimeUnit.NANOSECONDS.toMillis(difference) - TimeUnit.MINUTES.toMillis(secondsInDifference);
        System.out.format(
                "Total execution time: %d min, %d sec, %d milisec\n",
                minutesInDifference,
                secondsInDifference,
                milisecondsInDifference
        );
    }
}
class MyThread extends Thread implements Runnable{
    int from,to,sum;
    public MyThread(int from,int to){
        this.from=from;
        this.to=to;
        this.sum=0;

    }
    @Override
    public void run(){
        for(int i=from;i<=to;i++){
            sum+=1;
        }

    }
    public long getSum(){
        return this.sum;
    }
}