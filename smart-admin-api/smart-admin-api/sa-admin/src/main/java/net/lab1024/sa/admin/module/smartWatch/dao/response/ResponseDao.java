package net.lab1024.sa.admin.module.smartWatch.dao.response;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseVO;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface ResponseDao extends BaseMapper<ResponseEntity> {


    List<ResponseVO> queryPage(Page page, @Param("queryForm") ResponseQueryForm queryForm);

    void update(@Param("updateForm") ResponseUpdateForm updateForm);

    void deleteById(@Param("responseId") Long responseId);
}
