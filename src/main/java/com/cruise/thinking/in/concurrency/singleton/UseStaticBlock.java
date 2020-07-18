package com.cruise.thinking.in.concurrency.singleton;

import java.lang.reflect.ParameterizedType;

/**
 * 使用静态代码块
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseStaticBlock {

    private static UseStaticBlock instance = null;

    static {
        instance = new UseStaticBlock();
    }

    private UseStaticBlock() {

    }

    public static UseStaticBlock getInstance() {
        return instance;
    }
}
