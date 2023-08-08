package net.lab1024.sa.admin.module.system.role.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 *  Role Authority menu
 *
 */
@Data
@TableName("t_role_menu")
public class RoleMenuEntity {

    @TableId(type = IdType.AUTO)
    private Long roleMenuId;

    /**
     * role id
     */
    private Long roleId;

    /**
     * menu id
     */
    private Long menuId;

    /**
     * update time
     */
    private String updateTime;

    /**
     * create time
     */
    private String createTime;

}
