import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * LocalDateTime本身是没有时区概念的，而Instant是瞬时时间，
 * 所以toInstant才需要设置时区 LocalDateTime.now()是取本地时间，
 * 你再调用toInstant(ZoneOffset.of("+0"))是这个本地时间当成零时区的时间，所以差八个小时
 *
 * @author zhujiang.cheng
 * @since 2021/5/21
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());

        // 获取秒数 gmt+8
        Long second8 = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        // 获取毫秒数 gmt+8
        Long milliSecond8 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        // 获取秒数 gmt+0
        Long second0 = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+0"));
        // 获取毫秒数 gmt+0
        Long milliSecond0 = LocalDateTime.now().toInstant(ZoneOffset.of("+0")).toEpochMilli();

        // 获取秒数 currentTimeMillis
        long totalMilliSeconds = System.currentTimeMillis();

        System.out.println("+8 second:      " + second8);
        System.out.println("+8 milliSecond: " + milliSecond8);

        System.out.println("+0 seconds:     " + second0);
        System.out.println("+0 milliSeconds:" + milliSecond0);

        //显示时间
        System.out.println("+s seconds:     " + totalMilliSeconds);
    }
}
