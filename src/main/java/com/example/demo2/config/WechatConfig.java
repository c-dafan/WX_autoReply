package com.example.demo2.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {
    private String appid;
    private String appsecret;
    private String token;
}
