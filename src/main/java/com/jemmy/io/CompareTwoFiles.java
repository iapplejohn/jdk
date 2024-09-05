package com.jemmy.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author zhujiang.cheng
 * @since 2023/8/4
 */
public class CompareTwoFiles {

    public static void main(String[] args) throws IOException {
//        List<String> list1 = Files.readAllLines(Paths.get("/Users/xxx/Downloads/server_local"));
//        List<String> list2 = Files.readAllLines(Paths.get("/Users/xxx/Downloads/server_staging"));
        List<String> list1 = Files.readAllLines(Paths.get("/Users/xxx/Documents/1006_xxx/browser-extension/browser-extension-web/target/BOOT-INF/lib/local.txt"));
        List<String> list2 = Files.readAllLines(Paths.get("/Users/xxx/Downloads/tmp2/BOOT-INF/lib/local.txt"));


        for (int i = 0; i < list1.size(); i++) {
            String line1 = list1.get(i);
            String value1 = line1.substring(line1.indexOf('=') + 1).trim();
            String line2 = list2.get(i);
            String value2 = line2.substring(line2.indexOf('=') + 1).trim();
            if (!value1.equals(value2)) {
                System.err.println("line2 = " + line2);
            }
        }
    }
}
