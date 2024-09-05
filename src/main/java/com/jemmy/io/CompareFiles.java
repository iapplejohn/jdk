package com.jemmy.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author zhujiang.cheng
 * @since 2023/8/4
 */
public class CompareFiles {

    public static void main(String[] args) throws IOException {
        args = new String[]{"/Users/xxx/Downloads/seata_config_production.txt","/Users/xxx/Downloads/seata_config_test.txt","/Users/xxx/Downloads/seata_config_dev.txt"};

        TreeMap<String, String> baseMap = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[2]), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] array = line.split("=");
            baseMap.put(array[0], array[1]);
        }
        reader.close();

        TreeMap<String, String> compareMap = new TreeMap<>();
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
        String line2;
        while ((line2 = reader2.readLine()) != null) {
            String[] array = line2.split("=");
            compareMap.put(array[0], array[1]);
        }
        reader2.close();

        for (Entry<String, String> entry : baseMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!compareMap.containsKey(key)) {
                System.out.println("少了: " + key + "=" + value);
            } else {
                String compareValue = compareMap.get(key);
                if (!value.equals(compareValue)) {
                    System.out.println("不同: " + key + "=" + value + ", dev:" + compareValue);
                }
            }
        }

    }
}
