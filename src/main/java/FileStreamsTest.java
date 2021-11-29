import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

/**
 * @author zhujiang.cheng
 * @since 2021/1/22
 */
public class FileStreamsTest {

    private static final Map<String, Set<String>> fileListMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        recursive(Paths.get("/data/oalur"));
        System.out.println(fileListMap);

        getTimestamp();
    }

    private static void getTimestamp() throws IOException {
        Instant nowInstant = Instant.now().plus(-2, ChronoUnit.HOURS);
        FileTime nowTime = FileTime.from(nowInstant);
        System.out.println("current file time" + nowTime);

        // 遍历文件列表
        for (Map.Entry<String, Set<String>> entry: fileListMap.entrySet()) {
            Set<String> fileSet = entry.getValue();
            String key = entry.getKey();

            // 该业务文件列表还未完成
            if (!fileSet.isEmpty()) {
                int size = fileSet.size();
                int old = 0;
                for (String path : fileSet) {
                    FileTime fileTime = Files.getLastModifiedTime(Paths.get(path));
                    System.out.println(path + ">>>" + fileTime);
                    if (fileTime.compareTo(nowTime) < 0) {
                        old++;
                    }
                }

                if (old > 0 && old >= (size >>> 1)) {
                    //
                    System.out.println(" do merge " + key + " old: " + old);
                }
            }
        }
    }

    public static void recursive(Path path) {
        try (Stream<Path> streams = Files.list(path)) {
            streams.forEach(p -> {
                String pathStr = p.toString();
                if (Files.isDirectory(p)) {
                    recursive(p);
                } else {
                    int idx4 = pathStr.lastIndexOf(File.separatorChar);
                    int idx3 = pathStr.lastIndexOf(File.separatorChar, idx4 - 1);
                    int idx2 = pathStr.lastIndexOf(File.separatorChar, idx3 - 1);
                    int idx = pathStr.lastIndexOf(File.separatorChar, idx2 - 1);
                    String station = pathStr.substring(idx + 1, idx2);
                    String bizName = pathStr.substring(idx2 + 1, idx3);
                    String bizDate = pathStr.substring(idx3 + 1, idx4);
                    String bizKey = station + '_' + bizName + '_' + bizDate;

                    if (isValidDecompressedFile(pathStr)) {
                        Set<String> fileSet = fileListMap.get(bizKey);
                        if (fileSet == null) {
                            // 初始化该业务文件列表
                            fileSet = new CopyOnWriteArraySet<>();
                            fileListMap.put(bizKey, fileSet);
                        }

                        fileSet.add(pathStr);
                    }
                }
            });
        } catch (Exception e) {
            System.err.println("Fail to initialize fileListMap");
        }
    }

    private static boolean isValidDecompressedFile(String path) {
        // 是否解压后的文件
        boolean isDecompressedFile = !path.endsWith(".gz") && !path.endsWith(".log");

        // 是否有效：对应的压缩文件不存在
        return isDecompressedFile && !Files.exists(Paths.get(path + ".gz"));
    }
}
