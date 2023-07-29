package net.lab1024.sa.admin.module.system.role.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Role-user update
 *
 */
@Data
public class RoleUserUpdateForm {

    @ApiModelProperty("Role id")
    @NotNull(message = "Role id can not be null")
    protected Long roleId;

    @ApiModelProperty(value = "user Id list")
    @NotEmpty(message = "user id can not be null")
    protected List<Long> userIdList;

}
