package net.lab1024.sa.common.module.support.captcha.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Captcha form
 *
 */

@Data
public class CaptchaForm {

    @ApiModelProperty(value = "Captcha")
    @NotBlank(message = "Captcha can not be null")
    private String captchaCode;

    @ApiModelProperty(value = "Captcha id")
    @NotBlank(message = "Captcha id can not be null")
    private String captchaUuid;
}
