package net.lab1024.sa.admin.module.system.role.service;

import com.google.common.collect.Lists;
import net.lab1024.sa.admin.module.system.dao.menu.MenuDao;
import net.lab1024.sa.admin.module.system.menu.domain.entity.MenuEntity;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuSimpleTreeVO;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.admin.module.system.dao.role.RoleDao;
import net.lab1024.sa.admin.module.system.dao.role.RoleMenuDao;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleEntity;
import net.lab1024.sa.admin.module.system.role.domain.entity.RoleMenuEntity;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleMenuUpdateForm;
import net.lab1024.sa.admin.module.system.role.domain.vo.RoleMenuTreeVO;
import net.lab1024.sa.admin.module.system.role.manager.RoleMenuManager;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Role-Menu
 *
 */
@Service
public class RoleMenuService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private RoleMenuManager roleMenuManager;
    @Autowired
    private MenuDao menuDao;

    /**
     * Update role authorities
     *
     * @param roleMenuUpdateForm
     * @return
     */
    public ResponseDTO<String> updateRoleMenu(RoleMenuUpdateForm roleMenuUpdateForm) {
        //Check if the role exists
        Long roleId = roleMenuUpdateForm.getRoleId();
        RoleEntity roleEntity = roleDao.selectById(roleId);
        if (null == roleEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        List<RoleMenuEntity> roleMenuEntityList = Lists.newArrayList();
        RoleMenuEntity roleMenuEntity;
        for (Long menuId : roleMenuUpdateForm.getMenuIdList()) {
            roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setRoleId(roleId);
            roleMenuEntity.setMenuId(menuId);
            roleMenuEntityList.add(roleMenuEntity);
        }
        roleMenuManager.updateRoleMenu(roleMenuUpdateForm.getRoleId(), roleMenuEntityList);
        return ResponseDTO.ok();
    }

    /**
     * Acquire menu authorities
     *
     * @param roleIdList
     * @return
     */
    public List<MenuVO> getMenuList(List<Long> roleIdList, Boolean administratorFlag) {
        //Admin
        if(administratorFlag){
            List<MenuEntity> menuEntityList = roleMenuDao.selectMenuListByRoleIdList(Lists.newArrayList(), false);
            return SmartBeanUtil.copyList(menuEntityList, MenuVO.class);
        }
        //Normal account
        if (CollectionUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        List<MenuEntity> menuEntityList = roleMenuDao.selectMenuListByRoleIdList(roleIdList, false);
        return SmartBeanUtil.copyList(menuEntityList, MenuVO.class);
    }


    /**
     * Acquire menu of role
     *
     * @param roleId
     * @return
     */
    public ResponseDTO<RoleMenuTreeVO> getRoleSelectedMenu(Long roleId) {
        RoleMenuTreeVO res = new RoleMenuTreeVO();
        res.setRoleId(roleId);
        //Check selected menu
        List<Long> selectedMenuId = roleMenuDao.queryMenuIdByRoleId(roleId);
        res.setSelectedMenuId(selectedMenuId);
        //Check menu authorities
        List<MenuVO> menuVOList = menuDao.queryMenuList(Boolean.FALSE, Boolean.FALSE, null);
        Map<Long, List<MenuVO>> parentMap = menuVOList.stream().collect(Collectors.groupingBy(MenuVO::getParentId, Collectors.toList()));
        List<MenuSimpleTreeVO> menuTreeList = this.buildMenuTree(parentMap, NumberUtils.LONG_ZERO);
        res.setMenuTreeList(menuTreeList);
        return ResponseDTO.ok(res);
    }

    /**
     * Build menu tree
     *
     * @return
     */
    private List<MenuSimpleTreeVO> buildMenuTree(Map<Long, List<MenuVO>> parentMap, Long parentId) {
        List<MenuSimpleTreeVO> res = parentMap.getOrDefault(parentId, Lists.newArrayList()).stream()
                .map(e -> SmartBeanUtil.copy(e, MenuSimpleTreeVO.class)).collect(Collectors.toList());
        res.forEach(e -> {
            e.setChildren(this.buildMenuTree(parentMap, e.getMenuId()));
        });
        return res;
    }
}
