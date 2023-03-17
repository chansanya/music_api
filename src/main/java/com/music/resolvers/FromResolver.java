package com.music.resolvers;

import java.lang.reflect.Field;

import org.springframework.core.MethodParameter;
import org.springframework.web.service.invoker.HttpRequestValues;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @name: FromArgumentResolver
 * @author: leihuangyan
 * @classPath: com.music.resolvers.FromResolver
 * @date: 2023/3/17
 * @description:
 */
@Slf4j
public class FromResolver implements HttpServiceArgumentResolver {

    public FromResolver() {
        log.info("初始化自定义处理器");
    }

    @Override
    public boolean resolve(Object argument, MethodParameter parameter, HttpRequestValues.Builder requestValues) {

        FormBody annot = parameter.getParameterAnnotation(FormBody.class);
        if (annot == null) {
            return false;
        }

        log.info("进入自定义处理器");

        Field[] fields = ReflectUtil.getFields(argument.getClass());

        for (Field field : fields) {
            Object fieldValue = ReflectUtil.getFieldValue(argument, field);
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

            if(null != jsonProperty){
                log.info("JSON 属性： {}:{}",jsonProperty.value(),fieldValue);
                requestValues.addRequestParameter(jsonProperty.value(),fieldValue.toString());
            }else {
                log.info("NAME 属性名{}:{}",field.getName(),fieldValue);
                requestValues.addRequestParameter(field.getName(),fieldValue.toString());
            }
        }

        return true;
    }
}
