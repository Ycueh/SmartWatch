package net.lab1024.sa.admin.module.system.multiuser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserAddForm;
import net.lab1024.sa.admin.module.system.multiuser.service.MultiUserService;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Api(tags = {AdminSwaggerTagConst.Business.SW_MULTIUSER})
public class MultiUserController {

    @Autowired
    private MultiUserService multiUserService;

    @ApiOperation("Add new user")
    @PostMapping("/multiUser/add")
    public ResponseDTO<String> add(@RequestBody @Valid MultiUserAddForm addForm) {
        return multiUserService.add(addForm);
    }

    @ApiOperation("Delete user")
    @PostMapping("/multiUser/delete/{userId}")
    public ResponseDTO<String> delete(@PathVariable("userId") Long userId) {
        return multiUserService.delete(userId);
    }

    @ApiOperation("Choose user")
    @PostMapping("/multiUser/choose/{userId}")
    public ResponseDTO<String> choose(@PathVariable("userId") Long userId) {
        return multiUserService.choose(userId);
    }




}
