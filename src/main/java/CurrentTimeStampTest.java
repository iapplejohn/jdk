import java.util.TimeZone;

/**
 * @author zhujiang.cheng
 * @since 2021/5/21
 */
public class CurrentTimeStampTest {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(TimeZone.getDefault());
        System.out.println(System.getProperty("user.timezone"));
    }
}
