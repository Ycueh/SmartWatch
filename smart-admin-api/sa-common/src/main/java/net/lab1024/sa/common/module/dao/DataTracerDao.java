package net.lab1024.sa.common.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.common.module.support.datatracer.domain.entity.DataTracerEntity;
import net.lab1024.sa.common.module.support.datatracer.domain.form.DataTracerQueryForm;
import net.lab1024.sa.common.module.support.datatracer.domain.vo.DataTracerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dao： t_data_tracker
 *
 */
@Mapper
@Component
public interface DataTracerDao extends BaseMapper<DataTracerEntity> {

    /**
     * Page query
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<DataTracerVO> query(Page page, @Param("query") DataTracerQueryForm queryForm);
}
