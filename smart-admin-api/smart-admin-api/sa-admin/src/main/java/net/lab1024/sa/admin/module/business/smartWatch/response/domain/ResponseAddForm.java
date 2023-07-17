package net.lab1024.sa.admin.module.business.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 商品 添加表单
 *
 * @Author 1024创新实验室: 胡克
 * @Date 2021-10-25 20:26:54
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@Data
public class ResponseAddForm {

    /**
     * Response Date
     */
    @ApiModelProperty("Date")
    @NotNull(message = "Date could not be null")
    private String date;

    /**
     * Response Time
     */
    @ApiModelProperty("Time")
    @NotNull(message = "Time could not be null")
    private String time;

    /**
     * Response Time
     */
    @ApiModelProperty("responseTime")
    @NotNull(message = "responseTime could not be null")
    private String responseTime;

    /**
     * question ID
     */
    @ApiModelProperty("questionID")
    @NotNull(message = "questionID could not be null")
    private String questionID;

    /**
     * answer ID
     */
    @ApiModelProperty("answerID")
    @NotNull(message = "answerID could not be null")
    private String answerID;

    /**
     * question
     */
    @ApiModelProperty("question")
    @NotNull(message = "question could not be null")
    private String question;

    /**
     * response
     */
    @ApiModelProperty("response")
    @NotNull(message = "response could not be null")
    private String response;
}
