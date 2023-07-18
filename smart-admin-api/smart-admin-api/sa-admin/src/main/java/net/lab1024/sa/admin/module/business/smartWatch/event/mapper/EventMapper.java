package net.lab1024.sa.admin.module.business.smartWatch.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventEntity;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseUpdateForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EventMapper extends BaseMapper<EventEntity> {
    @Select("select * from Event where _id = #{id}")
    EventEntity selectById(Long id);

    void update(@Param("updateForm") EventUpdateForm updateForm);
}
