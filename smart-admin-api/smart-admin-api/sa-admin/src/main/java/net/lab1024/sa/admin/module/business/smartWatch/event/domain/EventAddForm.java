package net.lab1024.sa.admin.module.business.smartWatch.event.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventAddForm {

//    /**
//     * Event Time
//     */
//    @ApiModelProperty("eventTime")
//    private LocalTime eventtime;
//
//    /**
//     * Event date
//     */
//    @ApiModelProperty("eventDate")
//    private LocalDate eventdate;

    /**
     * event ID
     */
    @ApiModelProperty("eventID")
    @NotNull(message = "eventID could not be null")
    private Long eventid;


    /**
     * eventDescription
     */
    @ApiModelProperty("eventDescription")
    @NotNull(message = "eventDescription could not be null")
    private String eventdesc;

    /**
     * eventType
     */
    @ApiModelProperty("eventType")
    @NotNull(message = "eventType could not be null")
    private String eventtype;


}