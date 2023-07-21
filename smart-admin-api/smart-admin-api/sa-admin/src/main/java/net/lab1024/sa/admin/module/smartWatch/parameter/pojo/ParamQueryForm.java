package net.lab1024.sa.admin.module.smartWatch.parameter.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;

@Data
public class ParamQueryForm extends PageParam {
    @ApiModelProperty(value = "paramId")
    private String paramId;

    @ApiModelProperty(value = "keyword")
    private String keyword;

}
