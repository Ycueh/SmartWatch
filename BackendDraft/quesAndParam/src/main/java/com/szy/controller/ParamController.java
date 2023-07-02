package com.szy.controller;

import com.szy.pojo.ParamPageBean;
import com.szy.pojo.QuestionPageBean;
import com.szy.pojo.Parameter;
import com.szy.pojo.Result;
import com.szy.service.ParamService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parameters")
public class ParamController {
    @Autowired
    private ParamService paramService;

    @Operation(summary = "list parameters by page 分页查询 前端传2个参数，一个页码，一个每页的数据数。如果前端" +
            "没有传送数据 默认为 1 和 10")
    @GetMapping
    public Result pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        ParamPageBean PageBean = paramService.pageQuery(pageNum, pageSize);
        return Result.success(PageBean);
    }

    @Operation(summary = "select parameter by id  前端传入一个id 后端回传给前端此id对应的parameter数据")
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Parameter parameter = paramService.selectById(id);
        return Result.success(parameter);
    }
    @Operation(summary = "update the parameter value 更改parameterValue的流程是 前端点击编辑按钮后，发送给后端一个id，后端先根据id" +
            "找到指定question（即先使用selectById），然后后端会发送parameter数据给前端，前端修改后发送给后端，再使用这个接口,只能修改parameterValue")
    @PutMapping
    public Result updateParam(@RequestBody Parameter parameter){
        System.out.println(parameter);
        paramService.updateParam(parameter);
        return Result.success();
    }
}
