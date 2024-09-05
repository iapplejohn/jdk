package com.jemmy.algorithm;

import java.util.Arrays;

/**
 * @author zhujiang.cheng
 * @since 2022/6/14
 */
public class WeightRoundNginx implements WeightRound {

    class NginxWr {
        Element element;
        int current = 0;

        public NginxWr(Element element) {
            this.element = element;
        }
    }

    final NginxWr[] weights;

    public WeightRoundNginx(Element[] elements) {
        this.weights = Arrays.stream(elements)
            .map(NginxWr::new).toArray(NginxWr[]::new);
    }

    @Override
    public String next() {
        int total = 0;
        NginxWr shed = weights[0];

        for (NginxWr wr : weights) {
            int weight = wr.element.weight;
            total += weight;

            wr.current += weight;
            if (wr.current > shed.current) {
                shed = wr;
            }
        }

        shed.current -= total;
        return shed.element.peer;
    }
}
