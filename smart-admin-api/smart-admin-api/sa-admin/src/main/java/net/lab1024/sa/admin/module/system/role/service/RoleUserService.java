package net.lab1024.sa.admin.module.system.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleUserUpdateForm;
import net.lab1024.sa.admin.module.system.role.manager.RoleUserManager;
import net.lab1024.sa.admin.module.system.user.domain.vo.UserVO;
import net.lab1024.sa.admin.module.system.dao.role.RoleDao;
import net.lab1024.sa.admin.module.system.dao.role.RoleUserDao;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleUserEntity;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleEntity;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleUserQueryForm;
import net.lab1024.sa.admin.module.system.role.domain.vo.RoleSelectedVO;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.common.util.SmartPageUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色-员工
 *
 * @Author 1024创新实验室: 善逸
 * @Date 2021-10-22 23:17:47
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Service
public class RoleUserService {

    @Autowired
    private RoleUserDao roleUserDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleUserManager roleUserManager;

    /**
     * 通过角色id，分页获取成员员工列表
     *
     * @param roleUserQueryForm
     * @return
     */
    public ResponseDTO<PageResult<UserVO>> queryUser(RoleUserQueryForm roleUserQueryForm) {
        Page page = SmartPageUtil.convert2PageQuery(roleUserQueryForm);
        List<UserVO> userDTOS = roleUserDao.selectRoleUserByName(page, roleUserQueryForm)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        PageResult<UserVO> PageResult = SmartPageUtil.convert2PageResult(page, userDTOS, UserVO.class);
        return ResponseDTO.ok(PageResult);
    }

    public List<UserVO> getAllUserByRoleId(Long roleId) {
        return roleUserDao.selectUserByRoleId(roleId);
    }

    /**
     * 移除员工角色
     *
     * @param userId
     * @param roleId
     * @return ResponseDTO<String>
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeRoleUser(Long userId, Long roleId) {
        if (null == userId || null == roleId) {
            return ResponseDTO.userErrorParam();
        }
        roleUserDao.deleteByUserIdRoleId(userId, roleId);
        return ResponseDTO.ok();
    }

    /**
     * 批量删除角色的成员员工
     *
     * @param roleUserUpdateForm
     * @return ResponseDTO<String>
     */
    public ResponseDTO<String> batchRemoveRoleUser(RoleUserUpdateForm roleUserUpdateForm) {
        roleUserDao.batchDeleteUserRole(roleUserUpdateForm.getRoleId(), roleUserUpdateForm.getUserIdList());
        return ResponseDTO.ok();
    }

    /**
     * 批量添加角色的成员员工
     *
     * @param roleUserUpdateForm
     * @return
     */
    public ResponseDTO<String> batchAddRoleUser(RoleUserUpdateForm roleUserUpdateForm) {
        Long roleId = roleUserUpdateForm.getRoleId();
        List<Long> userIdList = roleUserUpdateForm.getUserIdList();
        // 保存新的角色员工
        List<RoleUserEntity> roleUserList = null;
        if (CollectionUtils.isNotEmpty(userIdList)) {
            roleUserList = userIdList.stream()
                    .map(userId -> new RoleUserEntity(roleId, userId))
                    .collect(Collectors.toList());
        }
        // 保存数据
        roleUserManager.saveRoleUser(roleId, roleUserList);
        return ResponseDTO.ok();
    }

    /**
     * 通过员工id获取员工角色
     *
     * @param userId
     * @return
     */
    public List<RoleSelectedVO> getRoleInfoListByUserId(Long userId) {
        List<Long> roleIds = roleUserDao.selectRoleIdByUserId(userId);
        List<RoleEntity> roleList = roleDao.selectList(null);
        List<RoleSelectedVO> result = SmartBeanUtil.copyList(roleList, RoleSelectedVO.class);
        result.stream().forEach(item -> item.setSelected(roleIds.contains(item.getRoleId())));
        return result;
    }

    /**
     * 根据员工id 查询角色id集合
     *
     * @param userId
     * @return
     */
    public List<Long> getRoleIdList(Long userId) {
        return roleUserDao.selectRoleIdByUserId(userId);
    }


}
