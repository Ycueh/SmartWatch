package net.lab1024.sa.admin.module.business.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.business.goods.constant.GoodsStatusEnum;
import net.lab1024.sa.common.common.domain.PageParam;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.common.validator.enumeration.CheckEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;

@Data
public class ResponseQueryForm extends PageParam {
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
    @ApiModelProperty(value = "responsetime")
    private String responsetime;

    /**
     * question ID
     */
    @ApiModelProperty(value = "questionid")
    private String questionid;

    /**
     * answer ID
     */
    @ApiModelProperty(value = "answerid")
    private String answerid;

    /**
     * question
     */
    @ApiModelProperty(value = "question")
    private String question;

    /**
     * response
     */
    @ApiModelProperty(value = "response")
    private String response;
}
