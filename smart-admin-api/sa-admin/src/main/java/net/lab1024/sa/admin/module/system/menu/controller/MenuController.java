package net.lab1024.sa.admin.module.system.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.common.AdminBaseController;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuTreeVO;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.admin.module.system.menu.service.MenuService;
import net.lab1024.sa.common.common.domain.RequestUrlVO;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Menu
 *
 */
@RestController
@Api(tags = {AdminSwaggerTagConst.System.SYSTEM_MENU})
public class MenuController extends AdminBaseController {

    @Autowired
    private MenuService menuService;

//    @ApiOperation(value = "Add menu")
//    @PostMapping("/menu/add")
//    @PreAuthorize("@saAuth.checkPermission('system:menu:add')")
//    public ResponseDTO<String> addMenu(@RequestBody @Valid MenuAddForm menuAddForm) {
//        menuAddForm.setCreateUserId(SmartRequestUtil.getRequestUserId());
//        return menuService.addMenu(menuAddForm);
//    }

//    @ApiOperation(value = "Update menu")
//    @PostMapping("/menu/update")
//    @PreAuthorize("@saAuth.checkPermission('system:menu:update')")
//    public ResponseDTO<String> updateMenu(@RequestBody @Valid MenuUpdateForm menuUpdateForm) {
//        menuUpdateForm.setUpdateUserId(SmartRequestUtil.getRequestUserId());
//        return menuService.updateMenu(menuUpdateForm);
//    }

//    @ApiOperation(value = "Batch delete menu")
//    @GetMapping("/menu/batchDelete")
//    @PreAuthorize("@saAuth.checkPermission('system:menu:delete,system:menu:batch:delete')")
//    public ResponseDTO<String> batchDeleteMenu(@RequestParam("menuIdList") List<Long> menuIdList) {
//        return menuService.batchDeleteMenu(menuIdList, SmartRequestUtil.getRequestUserId());
//    }

    @ApiOperation(value = "Query menu list")
    @GetMapping("/menu/query")
    public ResponseDTO<List<MenuVO>> queryMenuList() {
        return ResponseDTO.ok(menuService.queryMenuList(null));
    }

    @ApiOperation(value = "Get menu detail")
    @GetMapping("/menu/detail/{menuId}")
    public ResponseDTO<MenuVO> getMenuDetail(@PathVariable Long menuId) {
        return menuService.getMenuDetail(menuId);
    }

    @ApiOperation(value = "Query menu tree")
    @GetMapping("/menu/tree")
    public ResponseDTO<List<MenuTreeVO>> queryMenuTree(@RequestParam("onlyMenu") Boolean onlyMenu) {
        return menuService.queryMenuTree(onlyMenu);
    }

    @ApiOperation(value = "Get authorities url")
    @GetMapping("/menu/auth/url")
    public ResponseDTO<List<RequestUrlVO>> getAuthUrl() {
        return menuService.getAuthUrl();
    }
}
