package net.lab1024.sa.admin.module.system.role.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;

/**
 * Role-user search
 *
 */
@Data
public class RoleUserQueryForm extends PageParam {

    @ApiModelProperty("Keyword")
    private String keywords;

    @ApiModelProperty("role id")
    private String roleId;
}
