package net.lab1024.sa.admin.module.smartWatch.dataItem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemEntity;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemUpdateForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface DataItemMapper extends BaseMapper<DataItemEntity> {
    void update(@Param("updateForm") DataItemUpdateForm updateForm);

    void deleteById(@Param("dataitemId") Long dataitemId);

}
