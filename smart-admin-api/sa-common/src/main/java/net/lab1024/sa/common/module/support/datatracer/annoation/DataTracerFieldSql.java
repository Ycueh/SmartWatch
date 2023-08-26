    package net.lab1024.sa.common.module.support.datatracer.annoation;

    import com.baomidou.mybatisplus.core.mapper.BaseMapper;

    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;

    /**
     * Supports SQL query.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface DataTracerFieldSql {

        /**
         * Related column name.
         * @return
         */
        String relateColumn() default "id";

        /**
         * Displayed column related to the main column.
         * @return
         */
        String relateDisplayColumn() default "";
        /**
         * Whether to associate the field with a query Mapper.
         * @return
         */
        Class<? extends BaseMapper> relateMapper() default BaseMapper.class;

    }
