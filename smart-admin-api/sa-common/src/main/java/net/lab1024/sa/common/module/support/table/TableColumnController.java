package net.lab1024.sa.common.module.support.table;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.common.common.controller.SupportBaseController;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import net.lab1024.sa.common.constant.SwaggerTagConst;
import net.lab1024.sa.common.module.support.repeatsubmit.annoation.RepeatSubmit;
import net.lab1024.sa.common.module.support.table.domain.TableColumnUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Table column controller
 *
 */
@RestController
@Api(tags = {SwaggerTagConst.Support.TABLE_COLUMN})
public class TableColumnController extends SupportBaseController {

    @Autowired
    private TableColumnService tableColumnService;

    @ApiOperation("Edit")
    @PostMapping("/tableColumn/update")
    @RepeatSubmit
    public ResponseDTO<String> updateTableColumn(@RequestBody @Valid TableColumnUpdateForm updateForm) {
        return tableColumnService.updateTableColumns(SmartRequestUtil.getRequestUser(), updateForm);
    }

    @ApiOperation("Delete")
    @GetMapping("/tableColumn/delete/{tableId}")
    @RepeatSubmit
    public ResponseDTO<String> deleteTableColumn(@PathVariable Integer tableId) {
        return tableColumnService.deleteTableColumn(SmartRequestUtil.getRequestUser(), tableId);
    }

    @ApiOperation("Query")
    @GetMapping("/tableColumn/getColumns/{tableId}")
    public ResponseDTO<String> getColumns(@PathVariable Integer tableId) {
        return ResponseDTO.ok(tableColumnService.getTableColumns(SmartRequestUtil.getRequestUser(), tableId));
    }
}
