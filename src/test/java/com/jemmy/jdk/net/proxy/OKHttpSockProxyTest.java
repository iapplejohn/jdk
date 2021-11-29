package com.jemmy.jdk.net.proxy;

import com.jemmy.net.proxy.OKHttpSockProxy;
import java.io.IOException;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
public class OKHttpSockProxyTest extends BaseTest {

    @Resource
    private OKHttpSockProxy okHttpSockProxy;

    @Test
    public void testProxy() throws IOException {
        String boardingIp = "1.12.231.152";
        int boardingPort = 58133;
        String username = "xfggenlb";
        String password = "JqY5lNNFeOBc";

        String result = okHttpSockProxy.exec(boardingIp, boardingPort, username, password);
        Assertions.assertNotNull(result);
    }
}
