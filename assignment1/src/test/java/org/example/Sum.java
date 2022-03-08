package org.example;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
public class Sum {
    public static void main(String[]args){
        long startTime = System.nanoTime(); // Computation start time
        int sum=0;
        int num;
        num=(int)Math.pow(2, 32);
        for(int i=0;i<num;i++){
            sum+=1;
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
