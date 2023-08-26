package net.lab1024.sa.common.module.support.captcha.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Captcha vo
 *
 */
@Data
public class CaptchaVO {

    @ApiModelProperty("Verification code unique identifier")
    private String captchaUuid;

    @ApiModelProperty("Verification code image content - invalid in the production environment")
    private String captchaText;

    @ApiModelProperty("Verification code image in Base64 format")
    private String captchaBase64Image;

    @ApiModelProperty("Expiration time (seconds)")
    private Long expireSeconds;
}
