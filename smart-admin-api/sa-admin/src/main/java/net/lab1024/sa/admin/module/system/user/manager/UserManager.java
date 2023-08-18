package net.lab1024.sa.admin.module.system.user.manager;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.sa.admin.module.system.dao.user.UserDao;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleUserEntity;
import net.lab1024.sa.admin.module.system.role.manager.RoleUserManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.lab1024.sa.admin.module.system.user.domain.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User manager
 *
 */
@Service
public class UserManager extends ServiceImpl<UserDao, UserEntity> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserManager roleUserManager;

    /**
     * Save users
     *
     * @param user
     */
    @Transactional(rollbackFor = Throwable.class)
    public void saveUser(UserEntity user, List<Long> roleIdList) {
        // Save user
        userDao.insert(user);
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            List<RoleUserEntity> roleEmployeeList = roleIdList.stream().map(e -> new RoleUserEntity(e, user.getUserId())).collect(Collectors.toList());
            roleUserManager.saveBatch(roleEmployeeList);
        }
    }

    /**
     * Update user
     *
     * @param user
     */
    @Transactional(rollbackFor = Throwable.class)
    public void updateUser(UserEntity user,List<Long> roleIdList) {
        // Save user by id
        userDao.updateById(user);
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            List<RoleUserEntity> roleEmployeeList = roleIdList.stream().map(e -> new RoleUserEntity(e, user.getUserId())).collect(Collectors.toList());
            this.updateUserRole(user.getUserId(), roleEmployeeList);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateUserRole(Long userId, List<RoleUserEntity> roleEmployeeList) {
        roleUserManager.getBaseMapper().deleteByUserId(userId);

        if (CollectionUtils.isNotEmpty(roleEmployeeList)) {
            roleUserManager.saveBatch(roleEmployeeList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBatchById(List<UserEntity> userList) {
        if (CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(user -> userDao.updateById(user));
        }
    }

}
