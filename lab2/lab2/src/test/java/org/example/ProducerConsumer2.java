package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer2 {
    Queue<Integer> workingQueue = new LinkedList<Integer>();
    public synchronized void produce(int num) throws InterruptedException {
        workingQueue.add(num);
        while (workingQueue.size()>=10) {
            wait();
        }
        notifyAll();
    }
    public synchronized Integer consume() throws InterruptedException {
        while (workingQueue.isEmpty()) {
            wait();
        }
        return workingQueue.poll();
    }
}