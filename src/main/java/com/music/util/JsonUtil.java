package com.music.util;

import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.domain.resp.base.ApiResp;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER;


    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    public static ObjectMapper getObjectMapper(){
        return OBJECT_MAPPER;
    }

    /**
     * 装换成一个支持响应的 Resp
     * @param jsonStr json字符串
     * @param type 想要转换的复杂类型
     * @return  ApiResp类型为 泛型为 <T> 的对象
     * @param <T> 指定泛型
     */
    public static <T> ApiResp<T> toResp(String jsonStr,TypeReference<ApiResp<T>> type){
        return toBean(jsonStr, type);
    }

    /**
     * 装换成一个复杂 bean
     * @param jsonStr json字符串
     * @param type 想要转换的复杂类型
     * @return 泛型 T 对象
     * @param <T> 指定泛型
     */
    public static <T> T toBean(String jsonStr,TypeReference<T> type){
        Assert.hasText(jsonStr,"序列化字符串不能为空");
        try {
            return OBJECT_MAPPER.readValue(jsonStr, type);
        } catch (JsonProcessingException e) {
            log.error("JSON字符串反序列化失败",e);
            throw new RuntimeException("JSON字符串反序列化失败");
        }
    }

    /**
     * 装换成一个 简单bean
     * @param jsonStr json字符串
     * @param type 想要转换的Classs
     * @return 泛型 T 对象
     * @param <T> 指定泛型
     */
    public static <T> T toBean(String jsonStr,Class<T> type){
        Assert.hasText(jsonStr,"序列化字符串不能为空");
        try {
            return OBJECT_MAPPER.readValue(jsonStr, type);
        } catch (JsonProcessingException e) {
            log.error("JSON字符串反序列化失败",e);
            throw new RuntimeException("JSON字符串反序列化失败");
        }
    }

}
