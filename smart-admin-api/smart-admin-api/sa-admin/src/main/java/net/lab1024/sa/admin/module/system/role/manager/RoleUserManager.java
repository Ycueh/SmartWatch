package net.lab1024.sa.admin.module.system.role.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.sa.admin.module.system.role.dao.RoleUserDao;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleUserEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Role user manager
 *
 */
@Service
public class RoleUserManager extends ServiceImpl<RoleUserDao, RoleUserEntity> {

    /**
     * Save role user
     *
     * @param roleId
     * @param roleUserList
     */
    @Transactional(rollbackFor = Throwable.class)
    public void saveRoleUser(Long roleId, List<RoleUserEntity> roleUserList) {
        this.getBaseMapper().deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(roleUserList)) {
            this.saveBatch(roleUserList);
        }
    }
}
