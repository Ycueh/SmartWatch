package net.lab1024.sa.admin.module.smartWatch.response.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.smartWatch.dao.response.ResponseDao;
import net.lab1024.sa.admin.module.smartWatch.response.domain.*;
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

import java.util.*;

@Service
@Slf4j
public class ResponseService {
    @Autowired
    private ResponseDao resDAO;
    @Autowired
    private DataTracerService dataTracerService;

    /**
     * Add Response
     *
     * @param addForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ResponseAddForm addForm) {
        ResponseEntity resEntity = SmartBeanUtil.copy(addForm, ResponseEntity.class);
        resDAO.insert(resEntity);
        dataTracerService.insert(resEntity.getId(), DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    /**
     * Update Response
     *
     * @param updateForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ResponseUpdateForm updateForm) {
//        responseEntity originEntity = resDAO.selectById(updateForm.getId());
//        responseEntity responseEntity = SmartBeanUtil.copy(updateForm,responseEntity.class);
        //resDAO.updateById(responseEntity);
        resDAO.update(updateForm);
//        dataTracerService.update(updateForm.getId(), DataTracerTypeEnum.RESPONSE, originEntity, responseEntity);
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
        for(Long id:idList){
            resDAO.deleteById(id);
        }
        return ResponseDTO.ok();
    }
    /**
     * Delete one response
     */
    public synchronized ResponseDTO<String> delete(Long responseId) {
        if (null == responseId) {
            return ResponseDTO.ok();
        }
        resDAO.deleteById(responseId);
        return ResponseDTO.ok();
    }

    /**
     * Query page
     *
     * @param queryForm
     * @return
     */
    public PageResult<ResponseVO> queryPage(ResponseQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ResponseVO> list = resDAO.queryPage(page, queryForm);
        PageResult<ResponseVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }
}
