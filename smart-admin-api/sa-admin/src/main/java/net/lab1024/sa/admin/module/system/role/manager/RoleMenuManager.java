package net.lab1024.sa.admin.module.system.role.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.sa.admin.module.system.dao.role.RoleMenuDao;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Role-menu manager
 *
 */
@Service
public class RoleMenuManager extends ServiceImpl<RoleMenuDao, RoleMenuEntity> {

    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * Update role permission
     *
     * @param roleId
     * @param roleMenuEntityList
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenu(Long roleId, List<RoleMenuEntity> roleMenuEntityList) {
        roleMenuDao.deleteByRoleId(roleId);
        saveBatch(roleMenuEntityList);
    }
}
