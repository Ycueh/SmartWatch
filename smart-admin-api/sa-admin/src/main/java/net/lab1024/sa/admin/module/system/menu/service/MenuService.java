package net.lab1024.sa.admin.module.system.menu.service;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;
import net.lab1024.sa.admin.module.system.dao.menu.MenuDao;
import net.lab1024.sa.admin.module.system.menu.domain.entity.MenuEntity;
import net.lab1024.sa.admin.module.system.menu.domain.form.MenuAddForm;
import net.lab1024.sa.admin.module.system.menu.domain.form.MenuBaseForm;
import net.lab1024.sa.admin.module.system.menu.domain.form.MenuUpdateForm;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuTreeVO;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.common.common.code.SystemErrorCode;
import net.lab1024.sa.common.common.domain.RequestUrlVO;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单
 *
 * @Author 1024创新实验室: 善逸
 * @Date 2022-03-08 22:15:09
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private List<RequestUrlVO> authUrl;

    /**
     * Add menu
     *
     */
    public synchronized ResponseDTO<String> addMenu(MenuAddForm menuAddForm) {
        // Check menu na,e
        if (this.validateMenuName(menuAddForm)) {
            return ResponseDTO.userErrorParam("Menu name is existed");
        }
        // Check front-end permission
        if (this.validateWebPerms(menuAddForm)) {
            return ResponseDTO.userErrorParam("No permission parameter");
        }
        MenuEntity menuEntity = SmartBeanUtil.copy(menuAddForm, MenuEntity.class);
        // Api permission
        List<String> permsList = menuAddForm.getApiPermsList();
        if (!CollectionUtils.isEmpty(permsList)) {
            String perms = StringUtils.join(permsList, ",");
            menuEntity.setApiPerms(perms);
        }
        menuDao.insert(menuEntity);
        return ResponseDTO.ok();
    }

    /**
     * Update menu
     *
     * @param menuUpdateForm
     * @return
     */
    public synchronized ResponseDTO<String> updateMenu(MenuUpdateForm menuUpdateForm) {
        //Check if the menu exists
        MenuEntity selectMenu = menuDao.selectById(menuUpdateForm.getMenuId());
        if (selectMenu == null) {
            return ResponseDTO.userErrorParam("Menu does not exist");
        }
        if (selectMenu.getDeletedFlag()) {
            return ResponseDTO.userErrorParam("Menu is deleted");
        }
        //Check menu name
        if (this.validateMenuName(menuUpdateForm)) {
            return ResponseDTO.userErrorParam("Menu name exists");
        }
        // Check front-end permission
        if (this.validateWebPerms(menuUpdateForm)) {
            return ResponseDTO.userErrorParam("permission parameter exists");
        }
        if (menuUpdateForm.getMenuId().equals(menuUpdateForm.getParentId())) {
            return ResponseDTO.userErrorParam("Parent menu can not by itself");
        }
        MenuEntity menuEntity = SmartBeanUtil.copy(menuUpdateForm, MenuEntity.class);
        // Api permission
        List<String> permsList = menuUpdateForm.getApiPermsList();
        if (!CollectionUtils.isEmpty(permsList)) {
            String perms = StringUtils.join(permsList, ",");
            menuEntity.setApiPerms(perms);
        }
        menuDao.updateById(menuEntity);
        return ResponseDTO.ok();
    }


    /**
     * Batch delete menu
     *
     * @param menuIdList
     * @param userId
     * @return
     */
    public synchronized ResponseDTO<String> batchDeleteMenu(List<Long> menuIdList, Long userId) {
        if (CollectionUtils.isEmpty(menuIdList)) {
            return ResponseDTO.userErrorParam("Chosen menu can not be null");
        }
        menuDao.deleteByMenuIdList(menuIdList, userId, Boolean.TRUE);
        //Need to delete child node
        this.recursiveDeleteChildren(menuIdList, userId);
        return ResponseDTO.ok();
    }

    private void recursiveDeleteChildren(List<Long> menuIdList, Long userId) {
        List<Long> childrenMenuIdList = menuDao.selectMenuIdByParentIdList(menuIdList);
        if (CollectionUtil.isEmpty(childrenMenuIdList)) {
            return;
        }
        menuDao.deleteByMenuIdList(childrenMenuIdList, userId, Boolean.TRUE);
        recursiveDeleteChildren(childrenMenuIdList, userId);
    }

    /**
     * Check menu name
     *
     * @param menuDTO
     * @param <T>
     * @return true Existed false Non-existed
     */
    public <T extends MenuBaseForm> Boolean validateMenuName(T menuDTO) {
        MenuEntity menu = menuDao.getByMenuName(menuDTO.getMenuName(), menuDTO.getParentId(), Boolean.FALSE);
        if (menuDTO instanceof MenuAddForm) {
            return menu != null;
        }
        if (menuDTO instanceof MenuUpdateForm) {
            Long menuId = ((MenuUpdateForm) menuDTO).getMenuId();
            return menu != null && menu.getMenuId().longValue() != menuId.longValue();
        }
        return true;
    }

    /**
     * Check front end permission
     *
     * @param menuDTO
     * @param <T>
     * @return true Existed false Non-existed
     */
    public <T extends MenuBaseForm> Boolean validateWebPerms(T menuDTO) {
        MenuEntity menu = menuDao.getByWebPerms(menuDTO.getWebPerms(), Boolean.FALSE);
        if (menuDTO instanceof MenuAddForm) {
            return menu != null;
        }
        if (menuDTO instanceof MenuUpdateForm) {
            Long menuId = ((MenuUpdateForm) menuDTO).getMenuId();
            return menu != null && menu.getMenuId().longValue() != menuId.longValue();
        }
        return true;
    }

    /**
     * Search menu lsit
     *
     * @return
     */
    public List<MenuVO> queryMenuList(Boolean disabledFlag) {
        List<MenuVO> menuVOList = menuDao.queryMenuList(Boolean.FALSE, disabledFlag, null);
        //Group by parent id
        Map<Long, List<MenuVO>> parentMap = menuVOList.stream().collect(Collectors.groupingBy(MenuVO::getParentId, Collectors.toList()));
        List<MenuVO> filterMenuVOList = this.filterNoParentMenu(parentMap, NumberUtils.LONG_ZERO);
        return filterMenuVOList;
    }

    /**
     * Filter top level menu
     *
     * @param parentMap
     * @param parentId
     * @return
     */
    private List<MenuVO> filterNoParentMenu(Map<Long, List<MenuVO>> parentMap, Long parentId) {
        // Acquire current level list
        List<MenuVO> res = parentMap.getOrDefault(parentId, Lists.newArrayList());
        List<MenuVO> childMenu = Lists.newArrayList();
        // Acquire next level menu
        res.forEach(e -> {
            //Permission
            String perms = e.getApiPerms();
            if (StringUtils.isBlank(perms)) {
                e.setApiPermsList(Lists.newArrayList());
            } else {
                List<String> permsList = Lists.newArrayList(StringUtils.split(perms, ","));
                e.setApiPermsList(permsList);
            }
            List<MenuVO> menuList = this.filterNoParentMenu(parentMap, e.getMenuId());
            childMenu.addAll(menuList);
        });
        res.addAll(childMenu);
        return res;
    }

    /**
     * Search menu tree
     *
     * @param onlyMenu
     * @return
     */
    public ResponseDTO<List<MenuTreeVO>> queryMenuTree(Boolean onlyMenu) {
        List<Integer> menuTypeList = Lists.newArrayList();
        if (onlyMenu) {
            menuTypeList = Lists.newArrayList(MenuTypeEnum.CATALOG.getValue(), MenuTypeEnum.MENU.getValue());
        }
        List<MenuVO> menuVOList = menuDao.queryMenuList(Boolean.FALSE, null, menuTypeList);
        Map<Long, List<MenuVO>> parentMap = menuVOList.stream().collect(Collectors.groupingBy(MenuVO::getParentId, Collectors.toList()));
        List<MenuTreeVO> menuTreeVOList = this.buildMenuTree(parentMap, NumberUtils.LONG_ZERO);
        return ResponseDTO.ok(menuTreeVOList);
    }

    /**
     * Generate menu tree
     *
     * @return
     */
    List<MenuTreeVO> buildMenuTree(Map<Long, List<MenuVO>> parentMap, Long parentId) {
        // Acquire curren level menu list
        List<MenuTreeVO> res = parentMap.getOrDefault(parentId, Lists.newArrayList()).stream()
                .map(e -> SmartBeanUtil.copy(e, MenuTreeVO.class)).collect(Collectors.toList());
        res.forEach(e -> {
            String perms = e.getApiPerms();
            if (StringUtils.isBlank(perms)) {
                e.setApiPermsList(Lists.newArrayList());
            } else {
                List<String> permsList = Lists.newArrayList(StringUtils.split(perms, ","));
                e.setApiPermsList(permsList);
            }
            e.setChildren(this.buildMenuTree(parentMap, e.getMenuId()));
        });
        return res;
    }

    /**
     * Menu detail
     *
     * @param menuId
     * @return
     */
    public ResponseDTO<MenuVO> getMenuDetail(Long menuId) {
        //Check if the menu is existed
        MenuEntity selectMenu = menuDao.selectById(menuId);
        if (selectMenu == null) {
            return ResponseDTO.error(SystemErrorCode.SYSTEM_ERROR, "Menu does not exist");
        }
        if (selectMenu.getDeletedFlag()) {
            return ResponseDTO.error(SystemErrorCode.SYSTEM_ERROR, "Menu is deleted");
        }
        MenuVO menuVO = SmartBeanUtil.copy(selectMenu, MenuVO.class);
        String perms = menuVO.getApiPerms();
        if (!StringUtils.isBlank(perms)) {
            List<String> permsList = Lists.newArrayList(StringUtils.split(perms, ","));
            menuVO.setApiPermsList(permsList);
        }
        return ResponseDTO.ok(menuVO);
    }

    /**
     * Acquire permission url
     *
     * @return
     */
    public ResponseDTO<List<RequestUrlVO>> getAuthUrl() {
        return ResponseDTO.ok(authUrl);
    }

}
