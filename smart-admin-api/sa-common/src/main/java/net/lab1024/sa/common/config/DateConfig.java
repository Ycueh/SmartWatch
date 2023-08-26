package net.lab1024.sa.common.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * java8 localDate Time Format config
 *
 */
@Configuration
public class DateConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.deserializers(new LocalDateDeserializer(DatePattern.NORM_DATE_FORMAT.getDateTimeFormatter()));
            builder.deserializers(new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMAT.getDateTimeFormatter()));
            builder.serializers(new LocalDateSerializer(DatePattern.NORM_DATE_FORMAT.getDateTimeFormatter()));
            builder.serializers(new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMAT.getDateTimeFormatter()));
        };
    }


    /**
     * String to LocalDateTime
     *
     */
    @Configuration
    public static class StringToLocalDateTime implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String str) {
            if (StringUtils.isBlank(str)) {
                return null;
            }
            LocalDateTime localDateTime;
            try {
                localDateTime = LocalDateTimeUtil.parse(str, DatePattern.NORM_DATETIME_FORMAT.getDateTimeFormatter());
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Please input right date format：yyyy-MM-dd HH:mm:ss");
            }
            return localDateTime;
        }
    }


    /**
     * String to Localdate
     *
     */
    @Configuration
    public static class StringToLocalDate implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String str) {
            if (StringUtils.isBlank(str)) {
                return null;
            }
            LocalDate localDate;
            try {
                localDate = LocalDateTimeUtil.parseDate(str, DatePattern.NORM_DATE_FORMAT.getDateTimeFormatter());
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Please input right date format：yyyy-MM-dd");
            }
            return localDate;
        }
    }
}