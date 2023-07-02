package com.szy.service;

import com.szy.mapper.ParamMapper;
import com.szy.pojo.ParamPageBean;
import com.szy.pojo.QuestionPageBean;
import com.szy.pojo.Parameter;
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
