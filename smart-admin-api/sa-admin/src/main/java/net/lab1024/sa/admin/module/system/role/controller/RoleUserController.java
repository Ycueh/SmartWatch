package net.lab1024.sa.admin.module.system.role.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.common.AdminBaseController;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleUserUpdateForm;
import net.lab1024.sa.admin.module.system.user.domain.vo.UserVO;
import net.lab1024.sa.admin.module.system.role.domain.form.RoleUserQueryForm;
import net.lab1024.sa.admin.module.system.role.domain.vo.RoleSelectedVO;
import net.lab1024.sa.admin.module.system.role.service.RoleUserService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User of role
 *
 */
@RestController
@Api(tags = {AdminSwaggerTagConst.System.SYSTEM_ROLE_USER})
public class RoleUserController extends AdminBaseController {

    @Autowired
    private RoleUserService roleUserService;

    @ApiOperation(value = "Query User")
    @PostMapping("/role/user/queryUser")
    public ResponseDTO<PageResult<UserVO>> queryUser(@Valid @RequestBody RoleUserQueryForm roleUserQueryForm) {
        return roleUserService.queryUser(roleUserQueryForm);
    }

    @ApiOperation(value = "Query user by id")
    @GetMapping("/role/user/getAllUserByRoleId/{roleId}")
    public ResponseDTO<List<UserVO>> listAllUserRoleId(@PathVariable Long roleId) {
        return ResponseDTO.ok(roleUserService.getAllUserByRoleId(roleId));
    }

    @ApiOperation(value = "Remove user")
    @GetMapping("/role/user/removeUser")
    @PreAuthorize("@saAuth.checkPermission('system:role:user:delete')")
    public ResponseDTO<String> removeUser(Long userId, Long roleId) {
        return roleUserService.removeRoleUser(userId, roleId);
    }

    @ApiOperation(value = "Batch delete users")
    @PostMapping("/role/user/batchRemoveRoleUser")
    @PreAuthorize("@saAuth.checkPermission('system:role:user:batch:delete')")
    public ResponseDTO<String> batchRemoveUser(@Valid @RequestBody RoleUserUpdateForm updateForm) {
        return roleUserService.batchRemoveRoleUser(updateForm);
    }

    @ApiOperation(value = "batch add user")
    @PostMapping("/role/user/batchAddRoleUser")
    @PreAuthorize("@saAuth.checkPermission('system:role:user:add')")
    public ResponseDTO<String> addUserList(@Valid @RequestBody RoleUserUpdateForm addForm) {
        return roleUserService.batchAddRoleUser(addForm);
    }

    @ApiOperation(value = "Acquire all role")
    @GetMapping("/role/user/getRoles/{userId}")
    public ResponseDTO<List<RoleSelectedVO>> getRoleByUserId(@PathVariable Long userId) {
        return ResponseDTO.ok(roleUserService.getRoleInfoListByUserId(userId));
    }
}
