package net.lab1024.sa.admin.module.system.menu.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Menu Update Form
 *
 */
@Data
public class MenuUpdateForm extends MenuBaseForm {

    @ApiModelProperty("Menu ID")
    @NotNull(message = "Menu ID cannot be empty")
    private Long menuId;

    @ApiModelProperty(hidden = true)
    private Long updateUserId;
}
