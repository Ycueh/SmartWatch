package net.lab1024.sa.admin.module.smartWatch.question.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;

@Data
public class QuestionQueryForm extends PageParam {
    @ApiModelProperty(value = "questionID")
    private String questionId;

    @ApiModelProperty(value = "keyword")
    private String keyword;

}
