package net.lab1024.sa.admin.module.smartWatch.event.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EventVO {
    @ApiModelProperty(value = "_id")
    private Long Id;

    /**
     * event Date
     */
    @ApiModelProperty(value = "eventdate")
    private String eventdate;

    /**
     * event Time
     */
    @ApiModelProperty(value = "eventtime")
    private String eventtime;


    /**
     * event ID
     */
    private Long eventid;


    /**
     * event description
     */
    @ApiModelProperty(value = "eventdesc")
    private String eventdesc;

    /**
     * event type
     */
    @ApiModelProperty(value = "eventtype")
    private String eventtype;
}
