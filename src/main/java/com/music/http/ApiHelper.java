package com.music.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @name: ApiHelper
 * @author: leihuangyan
 * @classPath: com.music.http.ApiHelper
 * @date: 2023/3/15
 * @description:
 */
@Slf4j

public class ApiHelper {

    public void run(){

        Map<String, Long> map = new HashMap<>(3);
        map.put("timestamp",System.currentTimeMillis());

        WebClient webClient = WebClient.builder()
                .defaultHeaders(i->{
                    i.add("Access-Control-Allow-Origin", "https://y.qq.com");
                    i.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
                    i.add("Access-Control-Allow-Headers", "Content-Type");
                    i.add("Access-Control-Allow-Credentials", "true");
                })
                .defaultStatusHandler(HttpStatusCode::isError,resp->{
                    log.info("异常返回:{}",resp);

                     return Mono.create(i->{
                         log.info("异常返Mono回:{}",i);
                     });
                })
                .defaultUriVariables(map)
                .baseUrl("http://localhost:3000")
                .build();

//        timestamp
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        ApiLogin client = httpServiceProxyFactory.createClient(ApiLogin.class);


        String key = client.key();
        log.info("接口返回值:{}",key);
        System.out.println(key);


        String check = client.check("8744bb6c-5473-4227-ac70-9e80f50a3f89");
        log.info("接口返回值:{}",check);

        String qrcode = client.create("8744bb6c-5473-4227-ac70-9e80f50a3f89");
        log.info("接口返回值:{}",qrcode);
    }

    public static void main(String[] args) {
        new ApiHelper().run();
    }

}
