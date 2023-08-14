package net.lab1024.sa.admin.module.system.dao.multiuser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserEntity;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MultiUserMapper extends BaseMapper<MultiUserEntity> {

    void deleteById(@Param("userId") Long userId);

    MultiUserVO getFileByUserId(@Param("userId") Long userId);

    Long getIdByUserId(@Param("userId") Long userId);

    @Select("select file from t_user_db where t_user_db.user_id = #{userId}")
    String getFileNameByUserId(@Param("userId") Long userId);

    void updateFile(@Param("userId") Long userId, @Param("file_data") byte[] file_data);
}
