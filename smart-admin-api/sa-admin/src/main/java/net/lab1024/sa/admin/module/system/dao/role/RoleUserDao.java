package net.lab1024.sa.admin.module.system.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleUserQueryForm;
import net.lab1024.sa.admin.module.system.role.domain.vo.RoleUserVO;
import net.lab1024.sa.admin.module.system.user.domain.vo.UserVO;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface RoleUserDao extends BaseMapper<RoleUserEntity> {

    /**
     * Acquire user id according to role id
     * @param userId
     * @return
     */
    List<Long> selectRoleIdByUserId(@Param("userId") Long userId);
    /**
     *
     * @param page
     * @param roleUserQueryForm
     * @return
     */
    List<UserVO> selectRoleUserByName(Page page, @Param("queryForm") RoleUserQueryForm roleUserQueryForm);

    /**
     *
     * @param roleId
     * @return
     */
    List<UserVO> selectUserByRoleId(@Param("roleId") Long roleId);
    /**
     * Delete by user id
     * @param userId
     */
    void deleteByUserId(@Param("userId") Long userId);

    /**
     * Delete by role id
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId")Long roleId);

    /**
     *
     * @param userId
     * @param roleId
     */
    void deleteByUserIdRoleId(@Param("userId") Long userId,@Param("roleId")Long roleId);

    /**
     * Batch delete user under one role
     * @param roleId
     * @param userIds
     */
    void batchDeleteUserRole(@Param("roleId") Long roleId,@Param("userIds")List<Long> userIds);

    /**
     * Query all users according to role id
     * @param userIdList
     * @return
     */
    List<RoleUserVO> selectRoleByUserIdList(@Param("userIdList") List<Long> userIdList);

}
