package net.lab1024.sa.admin.module.smartWatch.dataItem.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class DataItemUpdateForm {
    @ApiModelProperty("id")
    @NotNull(message = "id could not be null")
    private Long id;
}
