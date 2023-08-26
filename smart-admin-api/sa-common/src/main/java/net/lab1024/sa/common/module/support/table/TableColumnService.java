package net.lab1024.sa.common.module.support.table;

import com.alibaba.fastjson.JSONArray;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.module.dao.TableColumnDao;
import net.lab1024.sa.common.module.support.table.domain.TableColumnEntity;
import net.lab1024.sa.common.module.support.table.domain.TableColumnUpdateForm;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Table column
 *
 */
@Service
public class TableColumnService {

    @Autowired
    private TableColumnDao tableColumnDao;

    /**
     * Acquire table column
     *
     * @return
     */
    public String getTableColumns(RequestUser requestUser, Integer tableId) {
        TableColumnEntity tableColumnEntity = tableColumnDao.selectByUserIdAndTableId(requestUser.getUserId(), requestUser.getUserType().getValue(), tableId);
        return tableColumnEntity == null ? null : tableColumnEntity.getColumns();
    }

    /**
     * Update
     *
     * @return
     */
    public ResponseDTO<String> updateTableColumns(RequestUser requestUser, TableColumnUpdateForm updateForm) {
        if (CollectionUtils.isEmpty(updateForm.getColumnList())) {
            return ResponseDTO.ok();
        }
        Integer tableId = updateForm.getTableId();
        TableColumnEntity tableColumnEntity = tableColumnDao.selectByUserIdAndTableId(requestUser.getUserId(), requestUser.getUserType().getValue(), tableId);
        if (tableColumnEntity == null) {
            tableColumnEntity = new TableColumnEntity();
            tableColumnEntity.setTableId(tableId);
            tableColumnEntity.setUserId(requestUser.getUserId());
            tableColumnEntity.setUserType(requestUser.getUserType().getValue());

            tableColumnEntity.setColumns(JSONArray.toJSONString(updateForm.getColumnList()));
            tableColumnDao.insert(tableColumnEntity);
        } else {
            tableColumnEntity.setColumns(JSONArray.toJSONString(updateForm.getColumnList()));
            tableColumnDao.updateById(tableColumnEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * Delete
     *
     * @return
     */
    public ResponseDTO<String> deleteTableColumn(RequestUser requestUser, Integer tableId) {
        tableColumnDao.delete(requestUser.getUserId(), requestUser.getUserType().getValue(), tableId);
        return ResponseDTO.ok();
    }
}
