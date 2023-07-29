package net.lab1024.sa.admin.module.system.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleUserQueryForm;
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
     * 根据员工id 查询所有的角色
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
     * 根据员工信息删除
     * @param userId
     */
    void deleteByUserId(@Param("userId") Long userId);

    /**
     * 删除某个角色的所有关系
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId")Long roleId);

    /**
     * 根据员工和 角色删除关系
     * @param userId
     * @param roleId
     */
    void deleteByUserIdRoleId(@Param("userId") Long userId,@Param("roleId")Long roleId);

    /**
     * 批量删除某个角色下的某批用户的关联关系
     * @param roleId
     * @param userIds
     */
    void batchDeleteUserRole(@Param("roleId") Long roleId,@Param("userIds")List<Long> userIds);
}
