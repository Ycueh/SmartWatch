package net.lab1024.sa.admin.module.smartWatch.event.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;
@Data
public class EventQueryForm extends PageParam {
    @ApiModelProperty(value = "eventdesc")
    private String eventdesc;

    @ApiModelProperty(value = "eventdate")
    private String eventdate;

    @ApiModelProperty(value = "eventtime")
    private String eventtime;

    @ApiModelProperty(value = "eventid")
    private String eventid;

    @ApiModelProperty(value = "eventtype")
    private String eventtype;

}
