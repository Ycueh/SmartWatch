package net.lab1024.sa.admin.module.system.menu.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.system.menu.domain.form.MenuBaseForm;

/**
 * Menu
 *
 */
@Data
public class MenuVO extends MenuBaseForm {

    @ApiModelProperty("Menu ID")
    private Long menuId;

    @ApiModelProperty("Creation Time")
    private String createTime;

    @ApiModelProperty("Creator")
    private Long createUserId;

    @ApiModelProperty("Update Time")
    private String updateTime;

    @ApiModelProperty("Updater")
    private Long updateUserId;
}
