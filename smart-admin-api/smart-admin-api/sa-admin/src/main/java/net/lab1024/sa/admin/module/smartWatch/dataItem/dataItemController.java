package net.lab1024.sa.admin.module.smartWatch.dataItem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemAddForm;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventAddForm;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.ValidateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_DATAITEM})
public class dataItemController {
    @Autowired
    private net.lab1024.sa.admin.module.smartWatch.dataItem.service.dataItemService dataItemService;

    @ApiOperation("Add dataItem")
    @PostMapping("/dataItem/add")
    public ResponseDTO<String> add(@RequestBody @Valid DataItemAddForm addForm) {
        return dataItemService.add(addForm);
    }

    @ApiOperation("Update dataItem")
    @PostMapping("/dataItem/update")
    public ResponseDTO<String> update(@RequestBody @Valid DataItemUpdateForm updateForm) {
        return dataItemService.update(updateForm);
    }

    @ApiOperation("Delete dataItem")
    @GetMapping("/dataItem/delete/{dataItemId}")
    public ResponseDTO<String> delete(@PathVariable("dataItemId") Long dataItemId) {
        return dataItemService.delete(dataItemId);
    }

    @ApiOperation("Group delete")
    @PostMapping("/dataItem/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody @Valid ValidateList<Long> idList) {
        return dataItemService.batchDelete(idList);
    }


}
