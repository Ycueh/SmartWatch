package net.lab1024.sa.admin.module.system.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.enumeration.GenderEnum;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;


import java.util.List;

/**
 * User info
 *
 */
@Data
public class UserVO {

    @ApiModelProperty("id")
    private Long userId;

    @ApiModelProperty("loginName")
    private String loginName;

    @ApiModelPropertyEnum(GenderEnum.class)
    private Integer gender;

    @ApiModelProperty("actualName")
    private String actualName;

    @ApiModelProperty("phone")
    private String phone;

    @ApiModelProperty("disabledFlag")
    private Boolean disabledFlag;

    @ApiModelProperty("administratorFlag")
    private Boolean administratorFlag;

    @ApiModelProperty("createTime")
    private String createTime;

    @ApiModelProperty("Role list")
    private List<Long> roleIdList;

    @ApiModelProperty("Role name list")
    private List<String> roleNameList;

    @ApiModelProperty("password")
    private String password;
}
