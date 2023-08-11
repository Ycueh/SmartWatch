package net.lab1024.sa.admin.module.system.role.domain.form;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Role add form
 *
 */
@Data
public class RoleAddForm {

    /**
     * Role name
     */
    @ApiModelProperty("Role Name")
    @NotNull(message = "Role Name can not be null")
    @Length(min = 1, max = 20, message = "Role name (1-20) characters")
    private String roleName;

    /**
     * Role remark
     */
    @ApiModelProperty("Role Remark")
    @Length(max = 255, message = "255 characters in maximum")
    private String remark;


}
