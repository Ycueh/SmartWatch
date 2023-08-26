package net.lab1024.sa.common.module.support.table.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Update form
 *
 */
@Data
public class TableColumnUpdateForm {

    @NotNull(message = "Table id can not be null")
    private Integer tableId;

    @NotEmpty(message = "Column list")
    private List<TableColumnItemForm> columnList;

}
