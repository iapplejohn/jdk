package com.jemmy.jdk8.parallel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author zhujiang.cheng
 * @since 2023/8/23
 */
public class ParallelStreamTest {

    public static void main(String[] args) {
//        threadSizeTest();
//        concurrentTest();
        concurrentTest2();
    }

    private static void concurrentTest2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Calendar> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Calendar startDay = new GregorianCalendar();
            Calendar checkDay = new GregorianCalendar();
            checkDay.setTime(startDay.getTime());
            checkDay.add(Calendar.DATE,i);
            list.add(checkDay);
        }
        list.forEach(day ->  System.out.println(formatter.format(day.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())));
        System.out.println("-----------------------");
        list.parallelStream().forEach(day ->  System.out.println(day.getTime()));
        System.out.println("+++++++++++++++++++++++");
        list.parallelStream().forEach(day ->  System.out.println(formatter.format(day.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())));
        System.out.println("-----------------------");
    }

    private static void concurrentTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Calendar> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Calendar startDay = new GregorianCalendar();
            Calendar checkDay = new GregorianCalendar();
            checkDay.setTime(startDay.getTime());
            checkDay.add(Calendar.DATE,i);
            list.add(checkDay);
        }
        list.forEach(day ->  System.out.println(sdf.format(day.getTime())));
        System.out.println("-----------------------");
        list.parallelStream().forEach(day ->  System.out.println(sdf.format(day.getTime())));
        System.out.println("-----------------------");
    }

    private static void threadSizeTest() {
        Set<Thread> threadSet = new HashSet<>();

        IntStream.range(0, 9).parallel()
            .forEach(record -> {
                System.out.println(Thread.currentThread().getName() + "--" + record);
                threadSet.add(Thread.currentThread());
            });

        System.out.println("thread size-" + threadSet.size());
    }
}
