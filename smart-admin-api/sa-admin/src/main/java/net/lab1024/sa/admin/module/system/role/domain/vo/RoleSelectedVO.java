package net.lab1024.sa.admin.module.system.role.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Selected roles
 *
 */
@Data
public class RoleSelectedVO extends RoleVO {

    @ApiModelProperty("Role name")
    private Boolean selected;
}
