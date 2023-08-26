package net.lab1024.sa.admin.module.system.menu.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Menu
 *
 */
@Data
public class MenuTreeVO extends MenuVO{

    @ApiModelProperty("Menu Subsets")
    private List<MenuTreeVO> children;
}
