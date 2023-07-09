package net.lab1024.sa.admin.module.business.smartWatch.parameter.service;


import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.ParamPageBean;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.Parameter;

public interface ParamService {
    Parameter selectById(Integer id);

    void updateParam(Parameter parameter);

    ParamPageBean pageQuery(Integer pageNum, Integer pageSize);
}
