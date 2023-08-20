package net.lab1024.sa.admin.module.smartWatch.parameter.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.parameter.pojo.ParamQueryForm;
import net.lab1024.sa.admin.module.smartWatch.parameter.pojo.Parameter;
import net.lab1024.sa.admin.module.smartWatch.parameter.service.ParamService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_PARAM})
public class ParamController {
    @Autowired
    private ParamService paramService;

    @ApiOperation("queryPage")
    @PostMapping("/parameter/queryPage")
    public ResponseDTO<PageResult<Parameter>> queryPage(@RequestBody @Valid ParamQueryForm queryForm) {
        return ResponseDTO.ok(paramService.queryPage(queryForm));
    }


    @ApiOperation("Get parameter by id")
    @GetMapping("/parameter/{id}")
    public ResponseDTO<Parameter> selectById(@PathVariable Integer id){
        Parameter parameter = paramService.selectById(id);
        return ResponseDTO.ok(parameter);
    }


    @ApiOperation("Update parameter")
    @PostMapping("/parameter/update")
    @PreAuthorize("@saAuth.checkPermission('smartWatch:parameter:edit')")
    public ResponseDTO<String> update(@RequestBody @Valid Parameter parameter) {
        paramService.updateParam(parameter);
        return ResponseDTO.ok();
    }
}
