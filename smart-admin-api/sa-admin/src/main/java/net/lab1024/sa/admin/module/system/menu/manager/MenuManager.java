package net.lab1024.sa.admin.module.system.menu.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;
import net.lab1024.sa.admin.module.system.dao.menu.MenuDao;
import net.lab1024.sa.admin.module.system.menu.domain.entity.MenuEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Menu Manager layer
 *
 */
@Service
public class MenuManager extends ServiceImpl<MenuDao, MenuEntity> {

    /**
     * Add a menu
     *
     * @param menuEntity
     * @param pointEntityList
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMenu(MenuEntity menuEntity, List<MenuEntity> pointEntityList) {
        // Add menu
        save(menuEntity);
        // Build function points
        pointEntityList.forEach(e -> {
            e.setParentId(menuEntity.getMenuId());
            e.setMenuType(MenuTypeEnum.POINTS.getValue());
            e.setCreateUserId(menuEntity.getCreateUserId());
        });
        // Batch add function points
        saveBatch(pointEntityList);
    }

    /**
     * Update menu
     *
     * @param menuEntity
     * @param savePointList
     * @param deletePointList
     * @param updatePointList
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuEntity menuEntity, List<MenuEntity> savePointList, List<MenuEntity> deletePointList, List<MenuEntity> updatePointList) {
        // Update menu
        updateById(menuEntity);
        // Build new function points
        savePointList.forEach(e -> {
            e.setParentId(menuEntity.getMenuId());
            e.setMenuType(MenuTypeEnum.POINTS.getValue());
            // Because the person updating is in the UpdateUserId field of menuEntity
            e.setCreateUserId(menuEntity.getUpdateUserId());
        });
        // Batch add function points
        saveBatch(savePointList);
        // Batch delete function points
        updateBatchById(deletePointList);
        // Batch update function points
        updateBatchById(updatePointList);
    }
}
