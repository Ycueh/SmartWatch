package net.lab1024.sa.admin.module.smartWatch.multiuser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventAddForm;
import net.lab1024.sa.admin.module.smartWatch.multiuser.domain.MultiUserAddForm;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Api(tags = {AdminSwaggerTagConst.Business.SW_MULTIUSER})
public class MultiUserController {

    @Autowired
    private net.lab1024.sa.admin.module.smartWatch.multiuser.service.MultiUserService multiUserService;

    @ApiOperation("Add new user")
    @PostMapping("/multiUser/add")
    public ResponseDTO<String> add(@RequestBody @Valid MultiUserAddForm addForm) {
        return multiUserService.add(addForm);
    }

}
