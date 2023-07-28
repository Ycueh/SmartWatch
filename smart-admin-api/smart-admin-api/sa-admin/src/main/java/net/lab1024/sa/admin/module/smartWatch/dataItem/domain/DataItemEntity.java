package net.lab1024.sa.admin.module.smartWatch.dataItem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@TableName("dataitem")
public class DataItemEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "_id")
    private Long id;

    @Column(name = "datestamp")
    String datestamp;

    @Column(name = "timestamp")
    String timestamp;

    @Column(name = "dataitem1")
    String dataitem1;

    @Column(name = "dataitem2")
    String dataitem2;

    @Column(name = "dataitem3")
    String dataitem3;

}
