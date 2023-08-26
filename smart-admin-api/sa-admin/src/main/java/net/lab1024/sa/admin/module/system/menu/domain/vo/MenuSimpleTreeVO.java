package net.lab1024.sa.admin.module.system.menu.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Simplified Menu VO (Value Object)
 *
 */
@Data
public class MenuSimpleTreeVO {

    @ApiModelProperty("Menu ID")
    private Long menuId;

    @ApiModelProperty("Menu Name")
    private String menuName;

    @ApiModelProperty("Function Point Associated Menu ID")
    private Long contextMenuId;

    @ApiModelProperty("Parent Menu ID")
    private Long parentId;

    @ApiModelProperty("Menu Type")
    private Integer menuType;

    @ApiModelProperty("Submenus")
    private List<MenuSimpleTreeVO> children;
}
