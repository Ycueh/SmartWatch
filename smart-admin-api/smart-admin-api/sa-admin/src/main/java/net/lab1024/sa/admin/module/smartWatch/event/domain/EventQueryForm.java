package net.lab1024.sa.admin.module.smartWatch.event.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;
@Data
public class EventQueryForm extends PageParam {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "eventdesc")
    private String eventdesc;

    @ApiModelProperty(value = "eventdate")
    private String eventdate;

    @ApiModelProperty(value = "eventtime")
    private String eventtime;

}
