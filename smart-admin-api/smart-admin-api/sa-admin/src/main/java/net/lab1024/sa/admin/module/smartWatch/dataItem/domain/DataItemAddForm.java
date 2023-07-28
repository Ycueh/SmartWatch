package net.lab1024.sa.admin.module.smartWatch.dataItem.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class DataItemAddForm {

    public DataItemAddForm() {
        InitTimeHere();
    }
    /**
     * Event Time
     */
    //@ApiModelProperty("timestamp")
    private String timestamp;


    /**
     * datestamp
     */
    @ApiModelProperty("datestamp")
    @NotNull(message = "datestamp could not be null")
    private String datestamp;

    /**
     * dataitem1
     */
    @ApiModelProperty("dataitem1")
    @NotNull(message = "dataitem1 could not be null")
    private String dataitem1;

    /**
     * dataitem2
     */
    @ApiModelProperty("dataitem2")
    @NotNull(message = "dataitem2 could not be null")
    private String dataitem2;

    /**
     * dataitem3
     */
    @ApiModelProperty("dataitem3")
    @NotNull(message = "dataitem3 could not be null")
    private String dataitem3;

    private void InitTimeHere() {
        LocalTime time = LocalTime.now();
        timestamp = time.format(DateTimeFormatter.ISO_TIME);
    }
}
