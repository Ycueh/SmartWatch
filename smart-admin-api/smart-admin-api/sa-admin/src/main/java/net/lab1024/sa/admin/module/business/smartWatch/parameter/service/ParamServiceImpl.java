package net.lab1024.sa.admin.module.business.smartWatch.parameter.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.mapper.ParamDAO;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.mapper.ParamMapper;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.ParamQueryForm;
import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.Parameter;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService{
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    private ParamDAO paramDAO;
    @Override
    public Parameter selectById(Integer id) {
        Parameter parameter = paramMapper.selectById(id);
        return parameter;
    }

    @Override
    public void updateParam(Parameter parameter) {
        paramMapper.updateParam(parameter);
    }

    @Transactional
    @Override
    public PageResult<Parameter> queryPage(ParamQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<Parameter> list = paramDAO.queryPage(page, queryForm);
        PageResult<Parameter> pageResult = SmartPageUtil.convert2PageResult(page, list);
       return pageResult;
    }

}
