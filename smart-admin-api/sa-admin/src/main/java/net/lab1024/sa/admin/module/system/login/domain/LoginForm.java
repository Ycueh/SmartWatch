package net.lab1024.sa.admin.module.system.login.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.common.util.SmartVerificationUtil;
import net.lab1024.sa.common.common.validator.enumeration.CheckEnum;
import net.lab1024.sa.common.module.support.captcha.domain.CaptchaForm;
import net.lab1024.sa.common.module.support.token.LoginDeviceEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
*  user login
 */
@Data
public class LoginForm extends CaptchaForm {

    @ApiModelProperty("Account")
    @NotBlank(message = "Account")
    @Length(max = 30, message = "30 characters in maximum")
    private String loginName;

    @ApiModelProperty("password")
    @NotBlank(message = "password can not be null")
    @Pattern(regexp = SmartVerificationUtil.PWD_REGEXP, message = "Please enter a 6-15 digit password (numbers | upper and lower case letters | decimal points)")
    private String password;

}
