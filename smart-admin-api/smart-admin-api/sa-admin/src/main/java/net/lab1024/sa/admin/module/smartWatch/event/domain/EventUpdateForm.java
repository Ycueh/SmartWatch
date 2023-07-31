package net.lab1024.sa.admin.module.smartWatch.event.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseAddForm;

import javax.validation.constraints.NotNull;

@Data
public class EventUpdateForm extends EventAddForm {
    @ApiModelProperty("id")
    @NotNull(message = "id could not be null")
    private Long id;
}
