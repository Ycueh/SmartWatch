package net.lab1024.sa.common.module.support.datatracer.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import net.lab1024.sa.common.common.enumeration.BaseEnum;

/**
 * Datatracer type
 *
 */
@AllArgsConstructor
@Getter
public enum DataTracerTypeEnum implements BaseEnum {


    RESPONSE(1,"response"),

    ;

    private final Integer value;

    private final String desc;
}
