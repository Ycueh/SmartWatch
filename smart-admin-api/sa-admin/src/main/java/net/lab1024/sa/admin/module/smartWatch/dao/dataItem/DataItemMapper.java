package net.lab1024.sa.admin.module.smartWatch.dao.dataItem;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemEntity;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemQueryForm;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface DataItemMapper extends BaseMapper<DataItemEntity> {
    void update(@Param("updateForm") DataItemUpdateForm updateForm);

    void deleteById(@Param("dataitemId") Long dataitemId);

    List<DataItemVO> queryPage(Page page, @Param("queryForm") DataItemQueryForm queryForm);

}
