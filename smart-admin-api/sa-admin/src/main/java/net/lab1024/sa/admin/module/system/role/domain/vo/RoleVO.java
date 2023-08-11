package net.lab1024.sa.admin.module.system.role.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Role
 *
 */
@Data
public class RoleVO {

    @ApiModelProperty("role id")
    private Long roleId;

    @ApiModelProperty("role name")
    private String roleName;

    @ApiModelProperty("role remark")
    private String remark;
}
