package net.lab1024.sa.admin.module.system.user.service;

import net.lab1024.sa.admin.module.system.menu.constant.MenuPermsTypeEnum;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.admin.module.system.role.service.RoleUserService;
import net.lab1024.sa.admin.module.system.role.service.RoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User permission
 *
 */
@Service
public class UserPermissionService {

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private RoleMenuService roleMenuService;
    /**
     * Permission list
     *
     * @param menuAndPointsList
     */
    public Set<? extends GrantedAuthority> buildAuthorities(List<MenuVO> menuAndPointsList) {
        HashSet<String> permissionList = new HashSet<>();
        for (MenuVO menu : menuAndPointsList) {
            if(menu.getPermsType() == null){
                continue;
            }

            String perms = null;
            if(menu.getPermsType().equals(MenuPermsTypeEnum.SPRING_SECURITY.getValue())){
                perms = menu.getWebPerms();
            }else{
                perms = menu.getApiPerms();
            }

            if (StringUtils.isEmpty(perms)) {
                continue;
            }
            String[] split = perms.split(",");
            for (String perm : split) {
                permissionList.add(perm);
            }
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(permissionList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        return authorities;
    }

    /**
     *
     * @param userId
     * @return
     */
    public List<MenuVO> getUserMenuAndPointsList(Long userId, Boolean administratorFlag) {
        List<Long> roleIdList = roleUserService.getRoleIdList(userId);
        return roleMenuService.getMenuList(roleIdList, administratorFlag);
    }

}
