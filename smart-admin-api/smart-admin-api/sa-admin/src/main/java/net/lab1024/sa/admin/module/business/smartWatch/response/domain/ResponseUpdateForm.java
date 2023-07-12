package net.lab1024.sa.admin.module.business.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.business.goods.domain.form.GoodsAddForm;

import javax.validation.constraints.NotNull;
@Data
public class ResponseUpdateForm extends ResponseAddForm {

    @ApiModelProperty("id")
    @NotNull(message = "id could not be null")
    private Long id;
}
