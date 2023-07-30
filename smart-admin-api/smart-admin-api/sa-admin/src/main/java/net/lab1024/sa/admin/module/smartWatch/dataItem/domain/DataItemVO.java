package net.lab1024.sa.admin.module.smartWatch.dataItem.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataItemVO {
    @ApiModelProperty(value = "_id")
    private Long Id;

    @ApiModelProperty(value = "datestamp")
    private String datestamp;

    @ApiModelProperty(value = "timestamp")
    private String timestamp;

    @ApiModelProperty(value = "dataitem1")
    private String dataitem1;

    @ApiModelProperty(value = "dataitem2")
    private String dataitem2;

    @ApiModelProperty(value = "dataitem3")
    private String dataitem3;

}
