package net.lab1024.sa.admin.module.system.menu.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.system.menu.constant.MenuPermsTypeEnum;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.common.validator.enumeration.CheckEnum;
import org.hibernate.validator.constraints.Length;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Menu base form
 *
 */
@Data
public class MenuBaseForm {

    @ApiModelProperty("Menu name")
    @NotBlank(message = "Menu name cannot be empty")
    @Length(max = 30, message = "Maximum of 30 characters")
    private String menuName;

    @ApiModelPropertyEnum(value = MenuTypeEnum.class, desc = "Type")
    @CheckEnum(value = MenuTypeEnum.class, message = "Type Error")
    private Integer menuType;

    @ApiModelProperty("Parent menu ID, if there's no superior, pass 0")
    @NotNull(message = "Parent menu ID cannot be empty")
    private Long parentId;

    @ApiModelProperty("Display order")
    private Integer sort;

    @ApiModelProperty("Route address")
    private String path;

    @ApiModelProperty("Component path")
    private String component;

    @ApiModelProperty("Is it an external link?")
    @NotNull(message = "Whether it's an external link cannot be empty")
    private Boolean frameFlag;

    @ApiModelProperty("External link address")
    private String frameUrl;

    @ApiModelProperty("Is caching?")
    @NotNull(message = "Whether it's cached cannot be empty")
    private Boolean cacheFlag;

    @ApiModelProperty("Display status")
    @NotNull(message = "Display status cannot be empty")
    private Boolean visibleFlag;

    @ApiModelProperty("Disabled status")
    @NotNull(message = "Disabled status cannot be empty")
    private Boolean disabledFlag;

    @ApiModelPropertyEnum(value = MenuPermsTypeEnum.class, desc = "Permission type")
    @CheckEnum(value = MenuPermsTypeEnum.class, message = "Permission type error")
    private Integer permsType;

    @ApiModelProperty("Frontend permission string")
    private String webPerms;

    @ApiModelProperty("Backend permission string")
    private String apiPerms;

    @ApiModelProperty("Backend interface permission list (split)")
    private List<String> apiPermsList;

    @ApiModelProperty("Menu icon")
    private String icon;

    @ApiModelProperty("Function point associated menu ID")
    private Long contextMenuId;
}
