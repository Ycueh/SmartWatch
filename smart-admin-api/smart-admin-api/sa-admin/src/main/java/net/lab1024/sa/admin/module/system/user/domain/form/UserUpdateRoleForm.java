package net.lab1024.sa.admin.module.system.user.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * user update role
 *
 */
@Data
public class UserUpdateRoleForm {

    @ApiModelProperty("userid")
    @NotNull(message = "userid can not be null")
    private Long userId;

    @ApiModelProperty("user ids")
    @Size(max = 99, message = "99 roles in maximum")
    private List<Long> roleIdList;

}
