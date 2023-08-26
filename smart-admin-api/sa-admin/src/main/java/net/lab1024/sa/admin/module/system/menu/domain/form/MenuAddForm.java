package net.lab1024.sa.admin.module.system.menu.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Menu add form
 *
 */
@Data
public class MenuAddForm extends MenuBaseForm {

    @ApiModelProperty(hidden = true)
    private Long createUserId;
}
