package net.lab1024.sa.admin.module.system.user.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.common.validator.enumeration.CheckEnum;
import org.hibernate.validator.constraints.Length;
import net.lab1024.sa.common.common.util.SmartVerificationUtil;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Add User
 *
 */
@Data
public class UserAddForm {

    @ApiModelProperty("Name")
    @NotNull(message = "Name can not be null")
    @Length(max = 30, message = "Maximum 30 chars")
    private String actualName;

    @ApiModelProperty("Account")
    @NotNull(message = "Account can not be null")
    @Length(max = 30, message = "Maximum 30 chars")
    private String loginName;

    @ApiModelProperty("Disabled or not")
    @NotNull(message = "disabledFlag can not be null")
    private Boolean disabledFlag;

    @ApiModelProperty("Phone")
    @NotNull(message = "Phone Number can not be null")
    @Pattern(regexp = SmartVerificationUtil.PHONE_REGEXP, message = "Not a valid phone number")
    private String phone;

    @ApiModelProperty("Role list")
    private List<Long> roleIdList;
}
