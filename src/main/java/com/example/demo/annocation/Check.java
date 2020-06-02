package com.example.demo.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 参数校验
 * 通过添加注解，再切面验证
 * @author czy
 * @date 2020/04/23
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Check {
    // 字段校验规则，格式：字段名+校验规则+冒号+错误信息，例如：id<10:ID必须少于10
    String[] value();
}
