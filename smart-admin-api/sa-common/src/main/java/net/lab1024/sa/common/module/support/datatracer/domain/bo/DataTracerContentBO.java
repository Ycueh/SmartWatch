package net.lab1024.sa.common.module.support.datatracer.domain.bo;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * Change content.
 */
@Data
public class DataTracerContentBO {

    /**
     * Changed field.
     */
    private Field field;

    /**
     * Value of the changed field.
     */
    private Object fieldValue;

    /**
     * Description of the changed field.
     */
    private String fieldDesc;

    /**
     * Content of the change.
     */
    private String fieldContent;

}
