package com.szy.service;

import com.szy.pojo.ParamPageBean;
import com.szy.pojo.QuestionPageBean;
import com.szy.pojo.Parameter;

public interface ParamService {
    Parameter selectById(Integer id);

    void updateParam(Parameter parameter);

    ParamPageBean pageQuery(Integer pageNum, Integer pageSize);
}
