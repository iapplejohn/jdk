package com.jemmy.queue;

import java.util.PriorityQueue;

/**
 * @author zhujiang.cheng
 * @since 2021/10/6
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
//        naturalOrdering();
        naturalOrderFixedLength();
    }

    private static void naturalOrdering() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.offer(3);
        queue.offer(5);
        queue.offer(2);
        queue.offer(1);
        Integer a = queue.poll();
        System.out.println(a);
    }

    private static void naturalOrderFixedLength() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.offer(3);
        queue.offer(5);
        queue.offer(2);
        if (queue.size() > 2) {
            Integer val = queue.poll();
            System.out.println("poll " + val);
        }

        queue.offer(1);
        if (queue.size() > 2) {
            Integer val = queue.poll();
            System.out.println("poll " + val);
        }

        Integer a = queue.poll();
        System.out.println(a);
    }
}
