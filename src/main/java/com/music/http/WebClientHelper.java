package com.music.http;

import java.util.Arrays;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.music.consts.Const;
import com.music.resolvers.FromResolver;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @name: WebClientHelper
 * @author: leihuangyan
 * @classPath: com.music.http.WebClientHelper
 * @date: 2023/3/15
 * @description:
 */
@Slf4j

public class WebClientHelper {
    /**
     * 分号
     */
    public static final String SEM = ";";
    /**
     * 等号
     */
    public static final String EQU = "=";

    public static <T> T getApi(String baseurl, Class<T> t) {

        WebClient.Builder builder = WebClient.builder()
                .baseUrl(baseurl)
                .defaultStatusHandler(HttpStatusCode::isError, resp -> {
                    log.info("异常返回:{}", resp);
                    return Mono.create(i -> {
                        log.info("异常返Mono回:{}", i);
                    });
                });

        setHeaders(builder);



//        String cookieStr = "_qpsvr_localtk=0.9005662506546561;ptui_loginuin=984038622;uin=984038622;login_type=1;RK=qdu8bWlsXf;ptcz=7fef7ed79c1fd780284d8c587a92327e195a5684d00902af3452b08e805c47e2";
//        setCookie(builder,cookieStr);

        WebClient webClient = builder.build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .customArgumentResolver(new FromResolver())
                .build();

        return httpServiceProxyFactory.createClient(t);
    }



    private static void setCookie(WebClient.Builder builder,String cookieStr) {

        Arrays.stream(cookieStr.split(SEM)).forEach(i->{
            String[] cookieGroup = i.split(EQU);
            log.info("cookie:[{}]=[{}]",cookieGroup[0],cookieGroup[1]);
            builder.defaultCookie(cookieGroup[0],cookieGroup[1]);
        });

//        builder.defaultCookie("_qpsvr_localtk", "0.9005662506546561")
//                .defaultCookie("ptui_loginuin", "984038622")
//                .defaultCookie("_uin", "984038622")
//                .defaultCookie("login_type", "1")
//                .defaultCookie("RK", "qdu8bWlsXf")
//                .defaultCookie("ptcz", "7fef7ed79c1fd780284d8c587a92327e195a5684d00902af3452b08e805c47e2");
    }

    private static void setHeaders(WebClient.Builder builder){
        //必须
        builder.defaultHeader("Referer", Const.HEADER_ORIGIN);

        //        builder.defaultHeaders(i->{
//            i.add("Referer", Const.HEADER_ORIGIN);
//        });

    }



}
