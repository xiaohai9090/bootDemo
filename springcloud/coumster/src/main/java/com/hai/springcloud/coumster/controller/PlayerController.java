package com.hai.springcloud.coumster.controller;

import com.hai.springcloud.service.PlayerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/Hello")
public class PlayerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * interfaceClass = PlayerService.class,   //服务接口名
     * version = "1.0.0",  //服务版本，与服务提供者的版本一致
     * check = false,   //启动时检查提供者是否存在，true报错，false忽略
     * timeout = 3000,  //服务方法调用超时时间(毫秒)
     * retries = 3,   //远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
     * methods = @Method(name = "dubboT") //精确到服务接口的某个方法
     */
    @DubboReference(interfaceClass = PlayerService.class, version = "1.0.0")
    private PlayerService playerService;


    @Value("${service-url.nacos-coumster-service}")
    private String serviceUrl;


    @GetMapping("/coumster/{id}")
    public String getPlayer(@PathVariable("id")int id){
        return restTemplate.getForObject(serviceUrl + "/payment/" + id,String.class);
    }

    @GetMapping("/dubboT")
    public String dubboT() {
        return playerService.providerTest();
    }
}
