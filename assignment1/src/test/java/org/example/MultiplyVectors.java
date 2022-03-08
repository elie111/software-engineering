package org.example;
import java.util.Scanner;
public class MultiplyVectors {
    public static Scanner in=new Scanner(System.in);
   public static void main(String []args){
       int n,N,from,to,mul=0;
       int[] vec1,vec2;
       System.out.print("enter size of vector: ");
       n=in.nextInt();
       System.out.print("enter number of threads: ");
       N=in.nextInt();
       from=0;

       if(N>=n)
           N=n;

       to=n/N;
       vec1 = new int[n];
       vec2 = new int[n];
       for (int i = 0; i < n; i++) {
           vec1[i] = (int) (Math.random() * 10) + 1;
           vec2[i] = (int) (Math.random() * 10) + 1;

       }
       MyThread2[] threads = new MyThread2[N];
       for (int i=0;i<N;i++){
           threads[i] = new MyThread2(n,N,from,to,vec1,vec2);
           from=to;
           if(i!=N-1) {
               to += n / N;
           }
           else{
               to=n;
           }

       }

       for (MyThread2 thread : threads) {
           thread.start(); // start the threads
       }
       for (MyThread2 thread : threads) {
           try {
               thread.join(); // wait for the threads to terminate
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       for (MyThread2 thread : threads) {
           mul+=thread.getMul(); // start the threads
       }
       System.out.println("the multiplication of the two vectors is: "+mul);

        for(int i=0;i<n;i++){
            System.out.print(vec1[i]+", ");
        }
       System.out.println();
       for(int i=0;i<n;i++){
           System.out.print(vec2[i]+", ");
       }






   }


}
 class MyThread2 extends Thread implements Runnable{
    int n,N,from,to,mul=0;
    int[] vec1,vec2;
    public MyThread2(int n,int N,int from,int to,int[] vec1,int[] vec2){
        this.n=n;
        this.N=N;
        this.from=from;
        this.to=to;
        this.vec1=vec1;
        this.vec2=vec2;

    }
     @Override
     public void run(){
         for(int i=from;i<to;i++){
             this.mul+=this.vec1[i]*this.vec2[i];
         }

     }
    public int getMul(){

        return this.mul;
    }
}

