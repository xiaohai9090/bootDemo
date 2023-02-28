package com.hai.springcloud.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author xiaohai
 * @date 2023/2/26 14:58
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "white")
public class WhiteUrlConfig {

    private List<String> whiteUrls;

    public List<String> getWhiteUrls() {
        return whiteUrls;
    }

    public void setWhiteUrls(List<String> whiteUrls) {
        this.whiteUrls = whiteUrls;
    }
}
