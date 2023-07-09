package net.lab1024.sa.admin.module.business.smartWatch.parameter.controller;


import io.swagger.annotations.Api;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.business.smartWatch.Result;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.ParamPageBean;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.Parameter;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parameters")
@Api(tags = {AdminSwaggerTagConst.Business.SW_PARAM})
public class ParamController {
    @Autowired
    private ParamService paramService;


    @GetMapping
    public Result pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        ParamPageBean PageBean = paramService.pageQuery(pageNum, pageSize);
        return Result.success(PageBean);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Parameter parameter = paramService.selectById(id);
        return Result.success(parameter);
    }
    @PutMapping
    public Result updateParam(@RequestBody Parameter parameter){
        System.out.println(parameter);
        paramService.updateParam(parameter);
        return Result.success();
    }
}
