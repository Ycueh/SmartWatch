package net.lab1024.sa.admin.module.business.smartWatch.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseAddForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseVO;
import net.lab1024.sa.admin.module.business.smartWatch.response.service.responseService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.ValidateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_RESP})
public class questionController {

}
