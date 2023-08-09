package net.lab1024.sa.admin.module.smartWatch.dao.multiuser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.admin.module.smartWatch.multiuser.domain.MultiUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MultiUserMapper extends BaseMapper<MultiUserEntity> {

    void createNewTable();

    void deleteById(@Param("userId") Long userId);

    @Select("select file from t_user_db where userId = #{userId}")
    String getFileNameByUserId(@Param("userId") Long userId);

}
