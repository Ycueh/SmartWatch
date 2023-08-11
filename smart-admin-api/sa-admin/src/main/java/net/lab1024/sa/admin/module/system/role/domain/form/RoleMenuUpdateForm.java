package net.lab1024.sa.admin.module.system.role.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Role Menu Update
 */
@Data
public class RoleMenuUpdateForm {

    /**
     * Role id
     */
    @ApiModelProperty("Role id")
    @NotNull(message = "Role id can not be null")
    private Long roleId;

    /**
     * menu id list
     */
    @ApiModelProperty("Menu Id list")
    @NotNull(message = "Menu Id list can not be null")
    private List<Long> menuIdList;

}
