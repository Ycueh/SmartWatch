package net.lab1024.sa.admin.module.system.role.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuSimpleTreeVO;

import java.util.List;

/**
 * Role menu tree
 *
 */
@Data
public class RoleMenuTreeVO {

    @ApiModelProperty("Role id")
    private Long roleId;

    @ApiModelProperty("Role menu list")
    private List<MenuSimpleTreeVO> menuTreeList;

    @ApiModelProperty("Selected menu")
    private List<Long> selectedMenuId;
}
