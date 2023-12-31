package net.lab1024.sa.admin.module.smartWatch.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseAddForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseVO;
import net.lab1024.sa.admin.module.smartWatch.response.service.ResponseService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.ValidateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_RESP})
public class ResponseController {
    @Autowired
    private ResponseService resService;

    @ApiOperation("queryPage")
    @PostMapping("/response/queryPage")
    public ResponseDTO<PageResult<ResponseVO>> queryPage(@RequestBody @Valid ResponseQueryForm queryForm) {
        return ResponseDTO.ok(resService.queryPage(queryForm));
    }

    @ApiOperation("Add response")
    @PostMapping("/response/add")
    public ResponseDTO<String> add(@RequestBody @Valid ResponseAddForm addForm) {
        return resService.add(addForm);
    }

    @ApiOperation("Update response")
    @PostMapping("/response/update")
    public ResponseDTO<String> update(@RequestBody @Valid ResponseUpdateForm updateForm) {
        return resService.update(updateForm);
    }

    @ApiOperation("Delete response")
    @GetMapping("/response/delete/{responseId}")
    @PreAuthorize("@saAuth.checkPermission('smartWatch:response:delete')")
    public ResponseDTO<String> delete(@PathVariable("responseId") Long responseId) {
        return resService.delete(responseId);
    }

    @ApiOperation("Batch delete")
    @PostMapping("/response/batchDelete")
    @PreAuthorize("@saAuth.checkPermission('smartWatch:response:batchDelete')")
    public ResponseDTO<String> batchDelete(@RequestBody @Valid ValidateList<Long> idList) {
        return resService.batchDelete(idList);
    }
}
