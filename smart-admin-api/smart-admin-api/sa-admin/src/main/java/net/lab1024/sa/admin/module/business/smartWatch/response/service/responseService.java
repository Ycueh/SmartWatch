package net.lab1024.sa.admin.module.business.smartWatch.response.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.business.category.domain.entity.CategoryEntity;
import net.lab1024.sa.admin.module.business.goods.domain.vo.GoodsVO;
import net.lab1024.sa.admin.module.business.smartWatch.response.dao.responseDAO;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.*;
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
public class responseService {
    @Autowired
    private responseDAO resDAO;
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
        responseEntity resEntity = SmartBeanUtil.copy(addForm, responseEntity.class);
        resDAO.insert(resEntity);
        dataTracerService.insert(resEntity.get_id(), DataTracerTypeEnum.RESPONSE);
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
        responseEntity originEntity = resDAO.selectById(updateForm.get_id());
        responseEntity responseEntity = SmartBeanUtil.copy(updateForm,responseEntity.class);
        resDAO.updateById(responseEntity);
        dataTracerService.update(updateForm.get_id(), DataTracerTypeEnum.RESPONSE, originEntity, responseEntity);
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
        resDAO.deleteBatchIds(idList);
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
     * 分页查询
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
