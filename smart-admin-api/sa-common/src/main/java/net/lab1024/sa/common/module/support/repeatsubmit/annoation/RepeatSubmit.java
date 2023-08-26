package net.lab1024.sa.common.module.support.repeatsubmit.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RepeatSubmit {

    /**
     * Repeat submit interval
     *
     * @return
     */
    int value() default 300;

    /**
     * Max 30 seconds
     */
    int MAX_INTERVAL = 30000;
}
