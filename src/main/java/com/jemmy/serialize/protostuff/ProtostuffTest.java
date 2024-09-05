package com.jemmy.serialize.protostuff;

import com.jemmy.serialize.Address;
import java.nio.charset.StandardCharsets;

/**
 * @author zhujiang.cheng
 * @since 2023/2/22
 */
public class ProtostuffTest {

    public static void main(String[] args) {
        Address address = new Address();
        address.setDetail("test");
        String data = new String(ProtostuffUtil.serialize(address), StandardCharsets.UTF_8);

        Address env = ProtostuffUtil.deserialize(data.getBytes(StandardCharsets.UTF_8), Address.class);
    }
}
