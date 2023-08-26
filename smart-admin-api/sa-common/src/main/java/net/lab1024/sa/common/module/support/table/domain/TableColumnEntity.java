package net.lab1024.sa.common.module.support.table.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Table column
 *
 */
@Data
@TableName("t_table_column")
public class TableColumnEntity {

    @TableId(type = IdType.AUTO)
    private Long tableColumnId;

    /**
     * User id
     */
    private Long userId;

    /**
     * User type
     */
    private Integer userType;

    /**
     * Table id
     */
    private Integer tableId;

    /**
     * Table column
     */
    private String columns;

    private String createTime;

    private String updateTime;
}
