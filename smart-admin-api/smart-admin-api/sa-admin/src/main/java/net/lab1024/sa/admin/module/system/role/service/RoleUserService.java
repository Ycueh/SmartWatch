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
 * Role-User
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
     * Acquire user lists based on role
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
     * Remove user role
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
     * Batch delete role user
     *
     * @param roleUserUpdateForm
     * @return ResponseDTO<String>
     */
    public ResponseDTO<String> batchRemoveRoleUser(RoleUserUpdateForm roleUserUpdateForm) {
        roleUserDao.batchDeleteUserRole(roleUserUpdateForm.getRoleId(), roleUserUpdateForm.getUserIdList());
        return ResponseDTO.ok();
    }

    /**
     * batch add user to role
     *
     * @param roleUserUpdateForm
     * @return
     */
    public ResponseDTO<String> batchAddRoleUser(RoleUserUpdateForm roleUserUpdateForm) {
        Long roleId = roleUserUpdateForm.getRoleId();
        List<Long> userIdList = roleUserUpdateForm.getUserIdList();
        // Save new user
        List<RoleUserEntity> roleUserList = null;
        if (CollectionUtils.isNotEmpty(userIdList)) {
            roleUserList = userIdList.stream()
                    .map(userId -> new RoleUserEntity(roleId, userId))
                    .collect(Collectors.toList());
        }
        // Save data
        roleUserManager.saveRoleUser(roleId, roleUserList);
        return ResponseDTO.ok();
    }

    /**
     * Acquire role based on userId
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
     * Acquire role id based on user id
     *
     * @param userId
     * @return
     */
    public List<Long> getRoleIdList(Long userId) {
        return roleUserDao.selectRoleIdByUserId(userId);
    }


}
