package net.lab1024.sa.admin.module.smartWatch.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventEntity;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventQueryForm;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventVO;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EventMapper extends BaseMapper<EventEntity> {
    @Select("select * from Event where id = #{id}")
    EventEntity selectById(Long id);

    void update(@Param("updateForm") EventUpdateForm updateForm);

    List<EventVO> queryPage(Page page, @Param("queryForm") EventQueryForm queryForm);

    void deleteById(@Param("responseId") Long responseId);
}
