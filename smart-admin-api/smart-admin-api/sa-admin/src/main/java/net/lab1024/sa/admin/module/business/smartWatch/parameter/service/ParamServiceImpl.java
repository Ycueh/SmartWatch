package net.lab1024.sa.admin.module.business.smartWatch.parameter.service;


import net.lab1024.sa.admin.module.business.smartWatch.parameter.mapper.ParamMapper;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.ParamPageBean;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService{
    @Autowired
    private ParamMapper paramMapper;

    @Override
    public Parameter selectById(Integer id) {
        Parameter parameter = paramMapper.selectById(id);
        return parameter;
    }

    @Override
    public void updateParam(Parameter parameter) {
        paramMapper.updateParam(parameter);
    }

    @Override
    public ParamPageBean pageQuery(Integer pageNum, Integer pageSize) {
        Long count = paramMapper.count();
        List<Parameter> parameters = paramMapper.pageQuery((pageNum-1)*pageSize, pageSize);
        ParamPageBean PageBean = new ParamPageBean(count, parameters);
        return PageBean;
    }
}
