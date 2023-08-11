package net.lab1024.sa.admin.module.smartWatch.event.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class EventAddForm {
    public EventAddForm() {
        InitTimeHere();
    }
    /**
     * Event Time
     */
    //@ApiModelProperty("eventTime")
    private String eventtime;

    /**
     * Event date
     */
    //ApiModelProperty("eventDate")
    private String eventdate;


    /**
     * eventDescription
     */
    @ApiModelProperty("eventDescription")
    @NotNull(message = "eventDescription could not be null")
    private String eventdesc;

    private void InitTimeHere() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        eventdate = date.format(DateTimeFormatter.ISO_DATE);
        eventtime = time.format(DateTimeFormatter.ISO_TIME);
    }


}
