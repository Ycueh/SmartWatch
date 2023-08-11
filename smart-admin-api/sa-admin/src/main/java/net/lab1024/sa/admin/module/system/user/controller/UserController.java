package net.lab1024.sa.admin.module.system.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.common.AdminBaseController;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.system.user.domain.form.*;
import net.lab1024.sa.admin.module.system.user.domain.vo.UserVO;
import net.lab1024.sa.admin.module.system.user.service.UserService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User
 *
 */
@RestController
@Api(tags = {AdminSwaggerTagConst.System.SYSTEM_USER})
public class UserController extends AdminBaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/query")
    @ApiOperation(value = "Page Query User")
    public ResponseDTO<PageResult<UserVO>> query(@Valid @RequestBody UserQueryForm query) {
        return userService.queryUser(query);
    }

    @ApiOperation(value = "Add User")
    @PostMapping("/user/add")
//    @PreAuthorize("@saAuth.checkPermission('system:User:add')")
    public ResponseDTO<String> addUser(@Valid @RequestBody UserAddForm userAddForm) {
        return userService.addUser(userAddForm);
    }

    @ApiOperation(value = "Update User")
    @PostMapping("/user/update")
//    @PreAuthorize("@saAuth.checkPermission('system:user:update')")
    public ResponseDTO<String> updateUser(@Valid @RequestBody UserUpdateForm userUpdateForm) {
        return userService.updateUser(userUpdateForm);
    }

    @ApiOperation(value = "Disable user")
    @GetMapping("/user/update/disabled/{userId}")
//    @PreAuthorize("@saAuth.checkPermission('system:user:disabled')")
    public ResponseDTO<String> updateDisableFlag(@PathVariable Long userId) {
        return userService.updateDisableFlag(userId);
    }

    @ApiOperation(value = "BatchDelete user")
    @PostMapping("/user/update/batch/delete")
//    @PreAuthorize("@saAuth.checkPermission('system:user:delete')")
    public ResponseDTO<String> batchUpdateDeleteFlag(@RequestBody List<Long> userIdList) {
        return userService.batchUpdateDeleteFlag(userIdList);
    }

    @ApiOperation(value = "Change password")
    @PostMapping("/user/update/password")
    public ResponseDTO<String> updatePassword(@Valid @RequestBody UserUpdatePasswordForm updatePasswordForm) {
        updatePasswordForm.setUserId(SmartRequestUtil.getRequestUserId());
        return userService.updatePassword(updatePasswordForm);
    }

    @ApiOperation(value = "Reset password")
    @GetMapping("/user/update/password/reset/{userId}")
//    @PreAuthorize("@saAuth.checkPermission('system:user:password:reset')")
    public ResponseDTO<String> resetPassword(@PathVariable Integer userId) {
        return userService.resetPassword(userId);
    }

    @ApiOperation("Query all the users")
    @GetMapping("/user/queryAll")
    public ResponseDTO<List<UserVO>> queryAllUser(@RequestParam(value = "disabledFlag", required = false) Boolean disabledFlag) {
        return userService.queryAllUser(disabledFlag);
    }

}
