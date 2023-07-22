package net.lab1024.sa.admin.module.smartWatch.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventEntity;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventUpdateForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EventMapper extends BaseMapper<EventEntity> {
    @Select("select * from Event where id = #{id}")
    EventEntity selectById(Long id);

    void update(@Param("updateForm") EventUpdateForm updateForm);
}
