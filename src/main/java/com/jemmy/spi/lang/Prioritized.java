package com.jemmy.spi.lang;

import java.util.Comparator;

import static java.lang.Integer.compare;

/**
 * @author zhujiang.cheng
 * @since 2023/6/1
 */
public interface Prioritized extends Comparable<Prioritized> {

    Comparator<Object> COMPARATOR = (one, two) -> {
        boolean b1 = one instanceof Prioritized;
        boolean b2 = two instanceof Prioritized;

        if (b1 && !b2) {
            return -1;
        } else if (!b1 && b2) {
            return 1;
        } else if (b1 && b2) {
            return ((Prioritized) one).compareTo((Prioritized) two);
        } else {
            return 0;
        }
    };

    int MAX_PRIORITY = Integer.MIN_VALUE;

    /**
     * The minimum priority
     */
    int MIN_PRIORITY = Integer.MAX_VALUE;

    /**
     * Normal Priority
     */
    int NORMAL_PRIORITY = 0;

    default int getPriority() {
        return NORMAL_PRIORITY;
    }

    @Override
    default int compareTo(Prioritized other) {
        return compare(this.getPriority(), other.getPriority());
    }
}
