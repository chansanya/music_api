package com.music.http;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.music.consts.Const;
import com.music.resolvers.FromResolver;

import lombok.extern.slf4j.Slf4j;

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

        WebClient.Builder builder = WebClient.builder().baseUrl(baseurl);

        setHeaders(builder);

        String cookieStr = "_qpsvr_localtk=0.9005662506546561;ptui_loginuin=984038622;uin=984038622;login_type=1;RK=qdu8bWlsXf;ptcz=7fef7ed79c1fd780284d8c587a92327e195a5684d00902af3452b08e805c47e2";
//        setCookie(builder,cookieStr);

        WebClient webClient = builder.build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                //自定义解析器
                .customArgumentResolver(new FromResolver())
                .build();

        return httpServiceProxyFactory.createClient(t);
    }


    @SuppressWarnings("all")
    record CookGroup(String key,String val){};


    /**
     * 设置统一Cookie
     * @param builder WebClient.Builder
     */
    private static void setCookie(WebClient.Builder builder,String cookieStr) {

        List<CookGroup> cookGroupList = Arrays.stream(cookieStr.split(SEM)).map(WebClientHelper::parse).toList();

        for (CookGroup cookie : cookGroupList) {
            builder.defaultCookie(cookie.key,cookie.val);
        }
    }

    private static CookGroup parse(String cookieGroupStr) {
        String[] cookieGroup = cookieGroupStr.split(EQU);
        return new CookGroup(cookieGroup[0],cookieGroup[1]);
    }


    /**
     * 设置统一请求头
     * @param builder WebClient.Builder
     */
    private static void setHeaders(WebClient.Builder builder){
        //必须
        builder.defaultHeader("Referer", Const.HEADER_REFERER);
        builder.defaultHeader("Referer", Const.HEADER_REFERER_PLAYLIST);
        builder.defaultHeader("Accept", "text/javascript, application/json");

        //        builder.defaultHeaders(i->{
//            i.add("Referer", Const.HEADER_ORIGIN);
//        });

    }



}
