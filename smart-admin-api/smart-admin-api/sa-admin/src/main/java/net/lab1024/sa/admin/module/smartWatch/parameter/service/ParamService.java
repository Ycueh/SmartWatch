package net.lab1024.sa.admin.module.smartWatch.parameter.service;


import net.lab1024.sa.admin.module.smartWatch.parameter.pojo.ParamQueryForm;
import net.lab1024.sa.admin.module.smartWatch.parameter.pojo.Parameter;
import net.lab1024.sa.common.common.domain.PageResult;

public interface ParamService {
    Parameter selectById(Integer id);

    void updateParam(Parameter parameter);

    PageResult<Parameter> queryPage(ParamQueryForm queryForm);
}
