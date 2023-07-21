package net.lab1024.sa.admin.module.smartWatch.response.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class ResponseVO {
    @ApiModelProperty(value = "_id")
    private Long Id;

    /**
     * Response Date
     */
    @ApiModelProperty(value = "date")
    private String date;

    /**
     * Response Time
     */
    @ApiModelProperty(value = "time")
    private String time;

    /**
     * Response Time
     */
    private String responseTime;

    /**
     * question ID
     */
    private String questionID;

    /**
     * answer ID
     */
    private String answerID;

    /**
     * question
     */
    private String question;

    /**
     * response
     */
    @ApiModelProperty(value = "response")
    private String response;
}
