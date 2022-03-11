package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer2 {
    BlockingQueue<Integer> workingQueue = new LinkedBlockingDeque<>(10);
    public synchronized void produce(int num) throws InterruptedException {
        workingQueue.add(num);
//        while (workingQueue.size()>=10) {
//            wait();
//        }
        notifyAll();
    }
    public synchronized Integer consume() throws InterruptedException {
        while (workingQueue.isEmpty()) {
            wait();
        }
        return workingQueue.poll();
    }
}