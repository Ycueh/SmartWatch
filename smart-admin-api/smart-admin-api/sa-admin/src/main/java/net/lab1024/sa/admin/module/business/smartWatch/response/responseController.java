package net.lab1024.sa.admin.module.business.smartWatch.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseAddForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseVO;
import net.lab1024.sa.admin.module.business.smartWatch.response.service.responseService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.ValidateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_RESP})
public class responseController {
    @Autowired
    private responseService resService;

    @ApiOperation("queryPage")
    @PostMapping("/response/queryPage")
    public ResponseDTO<PageResult<ResponseVO>> queryPage(@RequestBody @Valid ResponseQueryForm queryForm) {
        return ResponseDTO.ok(resService.queryPage(queryForm));
    }

    @ApiOperation("Add response")
    @PostMapping("/response/add")
   // @PreAuthorize("@saAuth.checkPermis//sion('goods:add')")
    public ResponseDTO<String> add(@RequestBody @Valid ResponseAddForm addForm) {
        return resService.add(addForm);
    }

    @ApiOperation("Update response")
    @PostMapping("/response/update")
   // @PreAuthorize("@saAuth.checkPermission('goods:update')")
    public ResponseDTO<String> update(@RequestBody @Valid ResponseUpdateForm updateForm) {
        return resService.update(updateForm);
    }

    @ApiOperation("Delete response")
    @GetMapping("/response/delete/{responseId}")
   // @PreAuthorize("@saAuth.checkPermission('goods:delete')")
    public ResponseDTO<String> delete(@PathVariable("responseId") Long responseId) {
        return resService.delete(responseId);
    }

    @ApiOperation("Group delete")
    @PostMapping("/response/batchDelete")
  //  @PreAuthorize("@saAuth.checkPermission('goods:batchDelete')")
    public ResponseDTO<String> batchDelete(@RequestBody @Valid ValidateList<Long> idList) {
        return resService.batchDelete(idList);
    }
}
