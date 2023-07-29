package net.lab1024.sa.admin.module.smartWatch.dataItem.service;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemAddForm;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemEntity;
import net.lab1024.sa.admin.module.smartWatch.dataItem.domain.DataItemUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.dataItem.mapper.DataItemMapper;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventAddForm;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventEntity;
import net.lab1024.sa.admin.module.smartWatch.event.domain.EventUpdateForm;
import net.lab1024.sa.admin.module.smartWatch.event.mapper.EventMapper;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;
import net.lab1024.sa.common.module.support.datatracer.service.DataTracerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class dataItemService {
    @Autowired
    private DataItemMapper dataItemMapper;
    @Autowired
    private DataTracerService dataTracerService;

    /**
     * Add DataItem
     *
     * @param addForm
     * @return
     */

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(DataItemAddForm addForm) {
        DataItemEntity dataEntity = SmartBeanUtil.copy(addForm, DataItemEntity.class);
        dataItemMapper.insert(dataEntity);
        dataTracerService.insert(dataEntity.getId(), DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    /**
     * Update Event
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(DataItemUpdateForm updateForm) {
        dataItemMapper.update(updateForm);
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
        dataItemMapper.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * Delete one dataItem
     */
    public synchronized ResponseDTO<String> delete(Long dataItemId) {
        if (null == dataItemId) {
            return ResponseDTO.ok();
        }
        dataItemMapper.deleteById(dataItemId);
        return ResponseDTO.ok();
    }


}
