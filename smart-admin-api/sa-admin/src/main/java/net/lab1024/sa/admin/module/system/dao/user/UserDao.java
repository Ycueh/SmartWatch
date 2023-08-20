package net.lab1024.sa.admin.module.system.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.system.role.domain.vo.RoleUserVO;
import net.lab1024.sa.admin.module.system.user.domain.entity.UserEntity;
import net.lab1024.sa.admin.module.system.user.domain.form.UserQueryForm;
import net.lab1024.sa.admin.module.system.user.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * User dao
 */
@Mapper
@Component
public interface UserDao extends BaseMapper<UserEntity> {
    /**
     * Query User List
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<UserVO> queryUser(Page page, @Param("queryForm") UserQueryForm queryForm);


    /**
     * Query User by disabled and deleted flag
     */
    List<UserVO> selectUserByDisabledAndDeleted(@Param("disabledFlag") Boolean disabledFlag, @Param("deletedFlag") Boolean deletedFlag);


    /**
     * Update one user
     *
     * @param userId
     * @param disabledFlag
     */
    void updateDisableFlag(@Param("userId") Long userId, @Param("disabledFlag") Boolean disabledFlag);


    /**
     * Query by login name
     *
     * @param loginName
     * @param disabledFlag
     * @return
     */
    UserEntity getByLoginName(@Param("loginName") String loginName,
                                  @Param("disabledFlag") Boolean disabledFlag);


    /**
     * Query by actual name
     *
     * @param actualName
     * @param disabledFlag
     * @return
     */
    UserEntity getByActualName(@Param("actualName") String actualName,
                                   @Param("disabledFlag") Boolean disabledFlag
    );

    /**
     * Query by phone number
     *
     * @param phone
     * @param disabledFlag
     * @return
     */
    UserEntity getByPhone(@Param("phone") String phone, @Param("disabledFlag") Boolean disabledFlag);

    /**
     * Reset password
     *
     * @param userId
     * @param password
     * @return
     */
    Integer updatePassword(@Param("userId") Integer userId, @Param("password") String password);

}