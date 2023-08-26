package net.lab1024.sa.common.module.support.datatracer.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;

import java.time.LocalDateTime;

/**
 * Data record entity.
 */
@Data
@TableName("t_data_tracer")
public class DataTracerEntity {

    @TableId(type = IdType.AUTO)
    private Long dataTracerId;

    /**
     * Data ID.
     */
    private Long dataId;

    /**
     * Business type.
     * {@link DataTracerTypeEnum}
     */
    private Integer type;

    /**
     * Content.
     */
    private String content;

    /**
     * Differences: old data.
     */
    private String diffOld;

    /**
     * Differences: new data.
     */
    private String diffNew;

    /**
     * Extended field.
     */
    private String extraData;

    /**
     * User.
     */
    private Long userId;

    /**
     * User type.
     */
    private Integer userType;

    /**
     * Username.
     */
    private String userName;

    /**
     * Request IP.
     */
    private String ip;

    /**
     * User agent (request header).
     */
    private String userAgent;

    /**
     * Creation time.
     */
    private String createTime;

    /**
     * Update time.
     */
    private String updateTime;
}
