package net.lab1024.sa.admin.module.system.menu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.sa.admin.module.system.menu.constant.MenuPermsTypeEnum;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.common.validator.enumeration.CheckEnum;



/**
 * Menu entity
 *
 */
@Data
@TableName(value = "t_menu")
public class MenuEntity {

    /**
     * Menu id
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /**
     * Menu name
     */
    private String menuName;

    /**
     * Type
     *
     * @see MenuTypeEnum
     */
    private Integer menuType;

    /**
     * Parent menu id
     */
    private Long parentId;

    /**
     * Sort
     */
    private Integer sort;

    /**
     * Menu path
     */
    private String path;

    /**
     * Vue path
     */
    private String component;

    /**
     * Frame flag
     */
    private Boolean frameFlag;

    /**
     * Frame Url
     */
    private String frameUrl;

    /**
     * Cache flag: 1Y 0N
     */
    private Boolean cacheFlag;

    /**
     * Visible flag: 1Y 0N
     */
    private Boolean visibleFlag;

    /**
     * Disabled flag
     */
    private Boolean disabledFlag;

    /**
     * Back end permission
     */
    private String apiPerms;

    /**
     * Permission type
     */
    private Integer permsType;

    /**
     * Front end permission
     */
    private String webPerms;

    /**
     * Icon
     */
    private String icon;

    /**
     * Context menu id
     */
    private Long contextMenuId;

    /**
     * Deleted flag
     */
    private Boolean deletedFlag;

    /**
     * Create time
     */
    private String createTime;

    /**
     * Create user
     */
    private Long createUserId;

    /**
     * Update time
     */
    private String updateTime;

    /**
     * Update user
     */
    private Long updateUserId;
}
