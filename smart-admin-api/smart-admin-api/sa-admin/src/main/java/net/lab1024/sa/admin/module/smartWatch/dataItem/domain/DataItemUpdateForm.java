package net.lab1024.sa.admin.module.smartWatch.dataItem.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventAddForm;

import javax.validation.constraints.NotNull;
@Data
public class DataItemUpdateForm extends DataItemAddForm {
    @ApiModelProperty("id")
    @NotNull(message = "id could not be null")
    private Long id;
}
