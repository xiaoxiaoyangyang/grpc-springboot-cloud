package com.linshen.grpcspringcloudconsumer;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guozhiyang@sensetime.com
 * @Description: todo
 * @date 2023-01-16 17:57
 */
@Slf4j
@RestController
public class DiscoveryController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String getHello() {
        log.info("===========cloud-provider=============");
        return "welcome to new world";
    }


    /**
     * 探针检查响应类
     * @return
     */
    @RequestMapping("/health")
    public String health() {
        return "health";
    }

    /**
     * 返回远程调用的结果
     * @return
     */
    @RequestMapping("/getservicedetail")
    public String getservicedetail(
            @RequestParam(value = "servicename", defaultValue = "") String servicename) {
        return "Service [" + servicename + "]'s instance list : " + JSON.toJSONString(discoveryClient.getInstances(servicename));
    }

    /**
     * 返回发现的所有服务
     * @return
     */
    @RequestMapping("/services")
    public String services() {
        return this.discoveryClient.getServices().toString()
                + ", "
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
