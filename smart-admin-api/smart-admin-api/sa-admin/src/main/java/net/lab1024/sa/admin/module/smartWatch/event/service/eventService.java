package net.lab1024.sa.admin.module.smartWatch.event.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.smartWatch.event.domain.*;
import net.lab1024.sa.admin.module.smartWatch.event.mapper.EventMapper;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseQueryForm;
import net.lab1024.sa.admin.module.smartWatch.response.domain.ResponseVO;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.common.util.SmartPageUtil;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;
import net.lab1024.sa.common.module.support.datatracer.service.DataTracerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class eventService {
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private DataTracerService dataTracerService;

    /**
     * Add Event
     *
     * @param addForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(EventAddForm addForm) {
        EventEntity eveEntity = SmartBeanUtil.copy(addForm, EventEntity.class);
        eventMapper.insert(eveEntity);
        dataTracerService.insert(eveEntity.getId(), DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    /**
     * Update Event
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(EventUpdateForm updateForm) {
        eventMapper.update(updateForm);
        return ResponseDTO.ok();
    }

    /**
     * 删除
     */
    /**
     * Delete batchIds
     *
     * @param idList
     * @return
     */
    public synchronized ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return ResponseDTO.ok();
        }
        eventMapper.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }
    /**
     * Delete one event
     */
    public synchronized ResponseDTO<String> delete(Long eventId) {
        if (null == eventId) {
            return ResponseDTO.ok();
        }
        eventMapper.deleteById(eventId);
        return ResponseDTO.ok();
    }

    /**
     * Query event list
     *
     * @param queryForm
     * @return
     */
    public PageResult<EventVO> queryPage(EventQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<EventVO> list = eventMapper.queryPage(page, queryForm);
        PageResult<EventVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }
}
