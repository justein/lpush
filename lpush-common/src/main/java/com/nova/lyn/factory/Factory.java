package com.nova.lyn.factory;

import java.util.function.Supplier;

/**
 * 什么是函数式接口（Functional Interface）
 * 当然首先是一个接口，然后就是在这个接口里面只能有一个抽象方法。
 *加不加@FunctionalInterface对于接口是不是函数式接口没有影响，该注解知识提醒编译器去检查该接口是否仅包含一个抽象方法
 *
 * */
@FunctionalInterface
public interface Factory<T> extends Supplier<T> {
}
