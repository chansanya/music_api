package com.music.http;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.music.consts.Const;
import com.music.resolvers.FromResolver;

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


    public static <T> T getApi(String baseurl,Class<T> t){

        String cookieStr = ";=";
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
                .defaultCookie("_qpsvr_localtk","0.9005662506546561")
                .defaultCookie("ptui_loginuin","984038622")
                .defaultCookie("_uin","984038622")
                .defaultCookie("login_type","1")
                .defaultCookie("RK","qdu8bWlsXf")
                .defaultCookie("ptcz","7fef7ed79c1fd780284d8c587a92327e195a5684d00902af3452b08e805c47e2")
                .baseUrl(baseurl)
                .defaultHeader("origin", Const.HEADER_REFERER)
                .defaultHeader("Referer", Const.HEADER_ORIGIN)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .customArgumentResolver(new FromResolver())
                .build();

        return  httpServiceProxyFactory.createClient(t);
    }

}
