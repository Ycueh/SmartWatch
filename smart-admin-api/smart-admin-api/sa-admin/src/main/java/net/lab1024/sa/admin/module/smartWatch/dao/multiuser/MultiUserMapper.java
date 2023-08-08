package net.lab1024.sa.admin.module.smartWatch.dao.multiuser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.admin.module.smartWatch.multiuser.domain.MultiUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MultiUserMapper extends BaseMapper<MultiUserEntity> {

    void createNewTable();


}
