package net.lab1024.sa.admin.module.system.user.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.util.SmartVerificationUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Update password items
 *
 */
@Data
public class UserUpdatePasswordForm {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty("Original password")
    @NotBlank(message = "Original password can not be null")
    @Pattern(regexp = SmartVerificationUtil.PWD_REGEXP, message = "6-15 digits(number|Upper/lower case letters|.)")
    private String oldPassword;

    @ApiModelProperty("New password")
    @NotBlank(message = "New password can not be null")
    @Pattern(regexp = SmartVerificationUtil.PWD_REGEXP, message = "6-15 digits(number|Upper/lower case letters|.)")
    private String newPassword;
}
