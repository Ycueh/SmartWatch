package net.lab1024.sa.admin.module.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * Response Query form
 *
 */
@Data
public class ResponseUpdateForm extends ResponseAddForm {

    @ApiModelProperty("id")
    @NotNull(message = "id could not be null")
    private Long id;
}
