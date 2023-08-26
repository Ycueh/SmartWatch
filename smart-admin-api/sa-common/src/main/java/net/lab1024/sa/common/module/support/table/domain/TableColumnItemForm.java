package net.lab1024.sa.common.module.support.table.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table column form
 *
 */
@Data
public class TableColumnItemForm {

    @NotEmpty(message = "Column can not be null")
    private String columnKey;

    @ApiModelProperty("Width")
    private Integer width;

    @NotNull(message = "Show can not be null")
    private Boolean showFlag;

    @NotNull(message = "Sort can not be null")
    private Integer sort;


}
