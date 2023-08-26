package net.lab1024.sa.common.module.support.datatracer.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;

import javax.validation.constraints.NotNull;

/**
 * 查询表单
 *
 */
@Data
public class DataTracerQueryForm extends PageParam {

    @ApiModelPropertyEnum(DataTracerTypeEnum.class)
    private Integer type;

    @ApiModelProperty("data id")
    @NotNull(message = "data id can not be null")
    private Long dataId;

    @ApiModelProperty("keywords")
    private String keywords;
}
