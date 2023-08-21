package net.lab1024.sa.admin.module.system.user.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User entity
 *
 */
@Data
@TableName("t_user")
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * loginName
     */
    private String loginName;

    /**
     * loginPwd
     */
    private String loginPwd;

    /**
     * actualName
     */
    private String actualName;

    /**
     * phone
     */
    private String phone;

    /**
     * administratorFlag 0:N 1:Y
     */
    private Boolean administratorFlag;

    /**
     * disabledFlag 0:not disabled 1:disabled
     */
    private Boolean disabledFlag;

    /**
     * deletedFlag
     */
    private Boolean deletedFlag;

    /**
     * remark
     */
    private String remark;

    private String updateTime;

    private String createTime;


}
