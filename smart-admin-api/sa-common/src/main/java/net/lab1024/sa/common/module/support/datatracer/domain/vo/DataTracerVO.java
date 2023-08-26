package net.lab1024.sa.common.module.support.datatracer.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;

/**
 * Change record.
 */
@Data
public class DataTracerVO {

    @ApiModelProperty("Log ID")
    private Long dataTracerId;

    @ApiModelProperty("Document ID")
    private Long dataId;

    @ApiModelPropertyEnum(value = DataTracerTypeEnum.class, desc = "Business type")
    private Integer type;

    @ApiModelProperty("Operation content")
    private String content;

    @ApiModelProperty("Differences: old data")
    private String diffOld;

    @ApiModelProperty("Differences: new data")
    private String diffNew;

    @ApiModelProperty("Extended field")
    private String extraData;

    @ApiModelProperty("Operator")
    private Long userId;

    @ApiModelPropertyEnum(value = UserTypeEnum.class, desc = "User type")
    private Integer userType;

    @ApiModelProperty("Operator name")
    private String userName;

    @ApiModelProperty("User agent")
    private String userAgent;

    @ApiModelProperty("IP address")
    private String ip;

    @ApiModelProperty("Operation time")
    private String createTime;

}
