package net.lab1024.sa.admin.module.business.smartWatch.response.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseVO;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.responseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface responseDAO extends BaseMapper<responseEntity> {

    /**
     * 分页 查询商品
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ResponseVO> queryPage(Page page, @Param("queryForm") ResponseQueryForm queryForm);

}
