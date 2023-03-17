package com.music.resolvers;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @name: FormBody
 * @author: leihuangyan
 * @classPath: com.music.resolvers.FormBody
 * @date: 2023/3/17
 * @description:
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormBody {

}
