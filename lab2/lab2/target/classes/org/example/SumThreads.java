package org.example;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class SumThreads  {

    public static void main (String[]args){
        long startTime = System.nanoTime(); // Computation start time
        long sum=0,from=0,to,num;
        num=(long)Math.pow(2,32);
        to=num/10;

        MyThread[] threads = new MyThread[10]; // create an array of threads
int counter=0;
        for (int i = 0; i < 10; i++) {

counter++;
            // create threads
            threads[i] = new MyThread(from,to);
            from = to ;
            if(counter!=9) {

                to += num / 10;
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
                TimeUnit.NANOSECONDS.toMillis(difference) - TimeUnit.SECONDS.toMillis(secondsInDifference);
        System.out.format(
                "Total execution time: %d min, %d sec, %d milisec\n",
                minutesInDifference,
                secondsInDifference,
                milisecondsInDifference
        );
    }
}
class MyThread extends Thread implements Runnable{
    long from,to,sum;
    public MyThread(long from,long to){
        this.from=from;
        this.to=to;
        this.sum=0;

    }
    @Override
    public void run(){
        for(long i=from;i<to;i++){
            sum+=1;
        }

    }
    public long getSum(){
        return this.sum;
    }
}