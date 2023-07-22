package net.lab1024.sa.admin.module.smartWatch.event;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventAddForm;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.ValidateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_EVENT})
public class eventController {
    @Autowired
    private net.lab1024.sa.admin.module.smartWatch.event.service.eventService eventService;

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

    @ApiOperation("Delete event")
    @GetMapping("/event/delete/{eventId}")
    public ResponseDTO<String> delete(@PathVariable("eventId") Long eventId) {
        return eventService.delete(eventId);
    }

    @ApiOperation("Group delete")
    @PostMapping("/event/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody @Valid ValidateList<Long> idList) {
        return eventService.batchDelete(idList);
    }
}
