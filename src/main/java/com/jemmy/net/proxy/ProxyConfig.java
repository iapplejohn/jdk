package com.jemmy.net.proxy;

import java.net.PasswordAuthentication;
import java.net.Proxy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhujiang.cheng
 * @since 2021/10/28
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProxyConfig {

    private Proxy proxy;

    private PasswordAuthentication authentication;
}
