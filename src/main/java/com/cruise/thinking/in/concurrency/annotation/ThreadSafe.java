package com.cruise.thinking.in.concurrency.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 线程安全的标识
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface ThreadSafe {
}
