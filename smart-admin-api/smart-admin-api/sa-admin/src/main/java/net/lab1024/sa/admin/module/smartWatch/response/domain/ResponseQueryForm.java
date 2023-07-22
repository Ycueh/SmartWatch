package net.lab1024.sa.admin.module.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;


@Data
public class ResponseQueryForm extends PageParam {

    @ApiModelProperty(value = "keyword")
    private String keyword;
    @ApiModelProperty(value = "date")
    private String date;
    @ApiModelProperty(value = "time")
    private String time;
    @ApiModelProperty(value = "questionid")
    private String questionid;
    /**
     * answer ID
     */
    @ApiModelProperty(value = "answerid")
    private String answerid;


//    /**
//     * Response Date
//     */
//    @ApiModelProperty(value = "date")
//    private String date;
//
//    /**
//     * Response Time
//     */
//    @ApiModelProperty(value = "time")
//    private String time;
//
//    /**
//     * Response Time
//     */
//    @ApiModelProperty(value = "responsetime")
//    private String responsetime;
//
//    /**
//     * question ID
//     */

//    /**
//     * question
//     */
//    @ApiModelProperty(value = "question")
//    private String question;
//
//    /**
//     * response
//     */
//    @ApiModelProperty(value = "response")
//    private String response;
}
