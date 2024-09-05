package com.jemmy.serialize.jdk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2024/8/21
 */
public class WithdrawCompleteMessageTest {

    @Test
    public void serializeTest() throws IOException {
        WithdrawCompleteMessage message = new WithdrawCompleteMessage();
        message.setWithdrawId("123");
        message.setWithdrawType(1);
        message.setWithdrawStatus(1);
        message.setClientId("12345");
        message.setBeneficiaryUid("3333");
        message.setConvertWithdrawType("ccc");

        byte[] bytes = JdkSerializer.serialize(message);
        Files.write(Paths.get("WithdrawCompleteMessage.obj"), bytes);
    }

    @Test
    public void deserializeTest() throws IOException, ClassNotFoundException {
        byte[] bytes = Files.readAllBytes(Paths.get("WithdrawCompleteMessage.obj"));

        WithdrawCompleteMessage message = (WithdrawCompleteMessage) JdkDeserializer.deserialize(bytes);
        System.out.println("message = " + message);
    }

}
