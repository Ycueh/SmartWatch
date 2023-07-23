package net.lab1024.sa.common.module.support.reload.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ReloadResultVO {

    @ApiModelProperty("加载项标签")
    private String tag;

    @ApiModelProperty("参数")
    private String args;

    @ApiModelProperty("运行结果")
    private Boolean result;

    @ApiModelProperty("异常")
    private String exception;

    @ApiModelProperty("创建时间")
    private String createTime;
}
