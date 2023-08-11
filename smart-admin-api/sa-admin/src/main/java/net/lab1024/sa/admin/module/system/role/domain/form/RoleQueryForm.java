package net.lab1024.sa.admin.module.system.role.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;

/**
 * Role Query
 */
@Data
public class RoleQueryForm extends PageParam {

    @ApiModelProperty("Role Name")
    private String roleName;

    @ApiModelProperty("Role Id")
    private String roleId;
}
