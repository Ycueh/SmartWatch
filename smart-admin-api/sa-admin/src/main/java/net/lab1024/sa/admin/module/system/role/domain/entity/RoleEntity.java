package net.lab1024.sa.admin.module.system.role.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * Role
 *
 */
@Data
@TableName("t_role")
public class RoleEntity {
    @TableId(type = IdType.AUTO)
    private Long roleId;

    private String roleName;

    private String remark;

    /**
     * Update time
     */
    private String updateTime;

    /**
     * Create time
     */
    private String createTime;
}
