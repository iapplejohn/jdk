package com.jemmy.jdk.net.proxy;

import com.jemmy.net.proxy.OKHttpProxy;
import java.io.IOException;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
public class OKHttpProxyTest extends BaseTest {

    @Resource
    private OKHttpProxy okHttpProxy;

    @Test
    public void testProxy() throws IOException {
        String boardingIp = "1.12.217.49";
        int boardingPort = 58278;
        String username = "xfggenlb";
        String password = "JqY5lNNFeOBc";

        String result = okHttpProxy.exec(boardingIp, boardingPort, username, password);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testCrawler() throws IOException {
        String boardingIp = "1.12.231.152";
        int boardingPort = 58133;
        String username = "xfggenlb";
        String password = "JqY5lNNFeOBc";

        String url = "https://www.amazon.com/dp/B082P2GZR1?th=1&psc=1";
        url = "https://www.amazon.com/s?k=halloween%20decorations";

        String result = okHttpProxy.crawler(boardingIp, boardingPort, username, password, url);
        Assertions.assertNotNull(result);
    }

}
