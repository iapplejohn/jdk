package com.jemmy.io;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * @author zhujiang.cheng
 * @since 2020/5/9
 */
public class FileCopyTest {

    public static void main(String[] args) throws IOException {
        File srcFile = new File("source.txt");
        File destFile = new File("destination.txt");

        FileUtils.copyFile(srcFile, destFile);
    }
}
