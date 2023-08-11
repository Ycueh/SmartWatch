package net.lab1024.sa.admin.module.system.user.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Update the user
 *
 */
@Data
public class UserUpdateForm extends UserAddForm {

    @ApiModelProperty("userId")
    @NotNull(message = "userId can not be null")
    private Long userId;
}
