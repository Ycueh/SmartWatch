package net.lab1024.sa.admin.module.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;


@Data
public class ResponseQueryForm extends PageParam {

    @ApiModelProperty(value = "keyword")
    private String keyword;

}
