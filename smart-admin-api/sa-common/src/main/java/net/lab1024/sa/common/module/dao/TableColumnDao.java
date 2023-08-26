package net.lab1024.sa.common.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.lab1024.sa.common.module.support.table.domain.TableColumnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Customizable table columns (end-users customize table columns on the front-end and save them to the database)
 *
 */
@Mapper
public interface TableColumnDao extends BaseMapper<TableColumnEntity> {

    TableColumnEntity selectByUserIdAndTableId(@Param("userId") Long userId, @Param("userType") Integer userType, @Param("tableId") Integer tableId);

    void delete(@Param("userId") Long userId, @Param("userType") Integer userType, @Param("tableId") Integer tableId);
}
