package net.lab1024.sa.admin.module.system.dao.menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import net.lab1024.sa.admin.module.system.menu.domain.entity.MenuEntity;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;

import java.util.List;

/**
 * Menu dao
 *
 */
@Mapper
@Component
public interface MenuDao extends BaseMapper<MenuEntity> {

    /**
     * Search for menus at the same level based on the name.
     *
     * @param menuName    menu Name
     * @param parentId    parent Id
     * @param deletedFlag Deleted or not
     * @return
     */
    MenuEntity getByMenuName(@Param("menuName") String menuName, @Param("parentId") Long parentId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * Search for the menu based on the frontend permission string.
     *
     * @param webPerms    Front end String
     * @param deletedFlag Deleted or not
     * @return
     */
    MenuEntity getByWebPerms(@Param("webPerms") String webPerms, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * Delete menu by menu id
     *
     * @param menuIdList   menu id list
     * @param updateUserId update user id
     * @param deletedFlag  deleted or not
     */
    void deleteByMenuIdList(@Param("menuIdList") List<Long> menuIdList, @Param("updateUserId") Long updateUserId, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * query menu list
     *
     * @param deletedFlag  Deleted or not
     * @param disabledFlag Disabled or not
     * @param menuTypeList Menu type list
     * @return
     */
    List<MenuVO> queryMenuList(@Param("deletedFlag") Boolean deletedFlag, @Param("disabledFlag") Boolean disabledFlag, @Param("menuTypeList") List<Integer> menuTypeList);


    /**
     * 根据菜单ID 查询功能点列表
     *
     * @param menuId      菜单id
     * @param menuType    菜单类型
     * @param deletedFlag 删除标记
     * @return
     */
    List<MenuEntity> getPointListByMenuId(@Param("menuId") Long menuId, @Param("menuType") Integer menuType, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 根据员工ID查询菜单列表
     *
     * @param deletedFlag  是否删除
     * @param disabledFlag 禁用标识
     * @param userId   员工id
     * @return
     */
    List<MenuVO> queryMenuByUserId(@Param("deletedFlag") Boolean deletedFlag,
                                       @Param("disabledFlag") Boolean disabledFlag,
                                       @Param("userId") Long userId);

    /**
     * 根据菜单类型查询
     *
     * @param menuType     菜单类型
     * @param deletedFlag  删除
     * @param disabledFlag 禁用
     * @return
     */
    List<MenuEntity> queryMenuByType(@Param("menuType") Integer menuType,
                                     @Param("deletedFlag") Boolean deletedFlag,
                                     @Param("disabledFlag") Boolean disabledFlag);

    /**
     * 查询孩子id
     *
     * @param menuIdList
     * @return
     */
    List<Long> selectMenuIdByParentIdList(@Param("menuIdList") List<Long> menuIdList);
}
