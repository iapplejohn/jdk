package com.jemmy.io;

import com.jemmy.algorithm.Element;
import com.jemmy.algorithm.WeightRound;
import com.jemmy.algorithm.WeightRoundNginx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhujiang.cheng
 * @since 2023/5/23
 */
@Slf4j
public class FileWriteTest {

    public static void main(String[] args) throws InterruptedException {
        FileWriteTest test = new FileWriteTest();

        Map<String, Integer> stationWeightMap = new HashMap<>();
        stationWeightMap.put("cn", 0);
        stationWeightMap.put("us", 10);

        Map<String, BufferedWriter> nameFileMap = new HashMap<>(4);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.doWrite(stationWeightMap, nameFileMap);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.doWrite(stationWeightMap, nameFileMap);
            }
        });

        t1.start();
        t2.start();

        log.info("started");

        t1.join();
        t2.join();

        log.info("completed");
    }

    private boolean doWrite(Map<String, Integer> stationWeightMap, Map<String, BufferedWriter> nameFileMap) {
        boolean result = true;
        try (InputStream inputStream = new FileInputStream("/Users/xxx/Downloads/us-active-asin-20230521-1684598533756_origin");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);) {

            Element[] elements = new Element[stationWeightMap.size()];
            int i = 0;
            for (Entry<String, Integer> entry : stationWeightMap.entrySet()) {
                elements[i++] = new Element(entry.getKey(), entry.getValue());
            }
            WeightRound weightRound = new WeightRoundNginx(elements);

            for (Element element : elements) {

                String localFilePath = "/Users/xxx/Documents/" + element.getPeer() + "/us-active-asin-20230521-1684598533756";
                Files.createDirectories(Paths.get(localFilePath).getParent());

                nameFileMap.put(element.getPeer(), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localFilePath), StandardCharsets.UTF_8)));
            }

            String message;
            while ((message = br.readLine()) != null) {
                String next = weightRound.next();
                BufferedWriter writer = nameFileMap.get(next);
                writer.write(message);
                writer.write('\n');
            }
        } catch (Throwable e) {
            log.error("send task error happened: ", e);
            result = false;
            // 记录分发失败状态
        } finally {
            for (Entry<String, BufferedWriter> entry : nameFileMap.entrySet()) {
                try {
                    entry.getValue().close();
                } catch (IOException e) {
                    log.error("Fail to close the writer of " + entry.getKey(), e);
                }
            }
        }

        return result;
    }
}
