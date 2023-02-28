package com.hai.springcloud.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import com.hai.springcloud.gateway.config.WhiteUrlConfig;
import com.hai.springcloud.redis.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaohai
 * @date 2023/2/26 10:47
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static Logger log = Logger.getLogger(AuthGlobalFilter.class);

    @Autowired
    private WhiteUrlConfig whiteUrlConfig;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().toString();
        List<String> whiteUrls = whiteUrlConfig.getWhiteUrls();
        if (StringUtils.isBlank(path)) {
            return setUnauthorizedResponse(exchange);
        }

        // 白名单
        // 跳过不需要验证的路径
        if (ObjectUtil.isNotEmpty(whiteUrls)) {
            for (String whitePath : whiteUrls) {
                if (whitePath.contains(path)) {
                    return chain.filter(exchange);
                }
            }
        }


        // 验证token
//        headers.getFirst()


        // 放行
        return chain.filter(exchange);
    }


    /**
     * 验证失败
     * @param exchange
     * @return
     */
    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }


    /**
     * 值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    public static void main(String[] args) {
        log.error("error .....");
        log.info("info ....");
        log.warn("warn....");
        log.debug("debug......");

        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
