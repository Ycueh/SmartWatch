package net.lab1024.sa.admin.module.business.smartWatch.event.service;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventAddForm;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventEntity;
import net.lab1024.sa.admin.module.business.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.event.mapper.EventMapper;
import net.lab1024.sa.admin.module.business.smartWatch.response.dao.responseDAO;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseAddForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.ResponseUpdateForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.responseEntity;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;
import net.lab1024.sa.common.module.support.datatracer.service.DataTracerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityListeners;
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
}
