package com.nova.lyn.spi;

import java.lang.annotation.*;

/***
 * @ClassName: SPI
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午5:02
 * @version : V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {

    /**SPI 名称*/
    String value() default "";

    /**排序顺序*/
    int order() default 0;
}
