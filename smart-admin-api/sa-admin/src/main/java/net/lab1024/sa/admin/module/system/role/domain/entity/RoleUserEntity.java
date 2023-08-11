package net.lab1024.sa.admin.module.system.role.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * Role-User
 *
 */
@Data
@TableName("t_role_user")
public class RoleUserEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long userId;

    private String updateTime;

    private String createTime;

    public RoleUserEntity() {
    }

    public RoleUserEntity(Long roleId, Long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
