package net.lab1024.sa.admin.module.system.menu.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Menu Function Point Operation Form
 *
 */
@Data
public class MenuPointsOperateForm {

    @ApiModelProperty("Menu ID")
    private Long menuId;

    @ApiModelProperty("Function point name")
    @NotBlank(message = "Function point cannot be empty")
    @Length(max = 30, message = "Function point name can have a maximum of 30 characters")
    private String menuName;

    @ApiModelProperty("Disabled status")
    @NotNull(message = "Disabled status cannot be empty")
    private Boolean disabledFlag;

    @ApiModelProperty("Backend interface permission list")
    private List<String> apiPermsList;

    @ApiModelProperty("Permission string")
    private String webPerms;

    @ApiModelProperty("Function point associated menu ID")
    private Long contextMenuId;
}
