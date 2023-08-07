package net.lab1024.sa.admin.module.system.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    List<UserVO> queryUser(Page page, @Param("queryForm") UserQueryForm queryForm, @Param("departmentIdList") List<Long> departmentIdList);

    /**
     * Quey User
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
     * Query all the users
     *
     * @return
     */
    List<UserVO> listAll();

    List<UserVO> getUserByIds(@Param("userIds") Collection<Long> userIds);


    /**
     * Query one user info
     *
     * @param userId
     * @return
     */
    UserVO getUserById(@Param("userId") Long userId);


    /**
     * 获取所有
     *
     * @param leaveFlag
     * @param disabledFlag
     * @return
     */
    List<Long> getUserId(@Param("leaveFlag") Boolean leaveFlag, @Param("disabledFlag") Boolean disabledFlag);

    /**
     * 员工重置密码
     *
     * @param userId
     * @param password
     * @return
     */
    Integer updatePassword(@Param("userId") Integer userId, @Param("password") String password);

}