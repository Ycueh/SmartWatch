package net.lab1024.sa.common.common.swagger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import net.lab1024.sa.common.common.enumeration.BaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.AnnotatedElement;

import static springfox.documentation.schema.Annotations.findPropertyAnnotation;

/**
 * Swagger plugin for providing descriptions of enum class fields.
 * SWAGGER_PLUGIN_ORDER+1 is used to execute this configuration after the original ones.
 *
 */
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1)
public class SwaggerApiModelPropertyEnumPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiModelPropertyEnum> annotation = Optional.absent();

        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(findPropertyAnnotation(context.getBeanPropertyDefinition().get(), ApiModelPropertyEnum.class));
        }

        if (annotation.isPresent()) {
            ApiModelPropertyEnum anEnum = annotation.get();
            String enumInfo = BaseEnum.getInfo(anEnum.value());
            context.getBuilder()
                    .required(annotation.transform(toIsRequired()).or(false))
                    .description(anEnum.desc() + ":" + enumInfo)
                    .example(annotation.transform(toExample()).orNull())
                    .isHidden(anEnum.hidden());
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    static Function<ApiModelPropertyEnum, Boolean> toIsRequired() {
        return annotation -> annotation.required();
    }

    public static Optional<ApiModelPropertyEnum> findApiModePropertyAnnotation(AnnotatedElement annotated) {
        return Optional.fromNullable(AnnotationUtils.getAnnotation(annotated, ApiModelPropertyEnum.class));
    }

    static Function<ApiModelPropertyEnum, String> toExample() {
        return annotation -> {
            String example = annotation.example();
            if (StringUtils.isBlank(example)) {
                return "";
            }
            return example;
        };
    }
}
