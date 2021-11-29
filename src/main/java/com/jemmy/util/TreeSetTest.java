package com.jemmy.util;

import java.util.TreeSet;

/**
 * @author zhujiang.cheng
 * @since 2021/10/6
 */
public class TreeSetTest {

    public static void main(String[] args) {
        TreeSetTest instance = new TreeSetTest();

        int[] nums = new int[]{1, 2, 2, 5, 3, 5};
        int result = instance.thirdMax(nums);
        System.out.println("result = " + result);
    }

    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : nums) {
            set.add(num);

            if (set.size() > 3) {
                set.remove(set.first());
            }
        }

        return set.size() == 3 ? set.first() : set.last();
    }
}
