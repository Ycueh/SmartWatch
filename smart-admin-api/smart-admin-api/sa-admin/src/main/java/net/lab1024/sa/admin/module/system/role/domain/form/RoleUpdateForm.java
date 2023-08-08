package net.lab1024.sa.admin.module.system.role.domain.form;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Role Update Form
 *
 */
@Data
public class RoleUpdateForm extends RoleAddForm {

    /**
     * Role Id
     */
    @ApiModelProperty("Role Id")
    @NotNull(message = "Role Id can not be null")
    protected Long roleId;


}
