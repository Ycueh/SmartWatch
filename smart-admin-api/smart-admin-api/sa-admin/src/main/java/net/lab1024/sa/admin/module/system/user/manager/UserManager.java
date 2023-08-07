package net.lab1024.sa.admin.module.system.user.manager;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.sa.admin.module.system.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.lab1024.sa.admin.module.system.user.domain.entity.UserEntity;

/**
 * User manager
 *
 */
@Service
public class UserManager extends ServiceImpl<UserDao, UserEntity> {

    @Autowired
    private UserDao userDao;

    /**
     * Save users
     *
     * @param user
     */
    @Transactional(rollbackFor = Throwable.class)
    public void saveUser(UserEntity user) {
        // 保存员工 获得id
        userDao.insert(user);
    }

    /**
     * Update user
     *
     * @param user
     */
    @Transactional(rollbackFor = Throwable.class)
    public void updateUser(UserEntity user) {
        // 保存员工 获得id
        userDao.updateById(user);
    }


}
