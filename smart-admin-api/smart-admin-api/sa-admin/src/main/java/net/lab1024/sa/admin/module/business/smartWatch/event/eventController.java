package net.lab1024.sa.admin.module.business.smartWatch.event;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventAddForm;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.event.mapper.EventMapper;
import net.lab1024.sa.admin.module.business.smartWatch.event.service.eventService;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseAddForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.service.responseService;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_EVENT})
public class eventController {
    @Autowired
    private eventService eventService;

    @ApiOperation("Add event")
    @PostMapping("/event/add")
    public ResponseDTO<String> add(@RequestBody @Valid EventAddForm addForm) {
        return eventService.add(addForm);
    }

    @ApiOperation("Update event")
    @PostMapping("/event/update")
    public ResponseDTO<String> update(@RequestBody @Valid EventUpdateForm updateForm) {
        return eventService.update(updateForm);
    }
}
