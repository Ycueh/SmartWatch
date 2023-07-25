package net.lab1024.sa.admin.module.smartWatch.event.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import javax.persistence.*;

@Data
@TableName("event")
public class EventEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "_id")
    private Long id;

    @Column(name = "eventdate")
    String eventdate;

    @Column(name = "eventtime")
    String eventtime;

    @Column(name = "eventdesc")
    private String eventdesc;

}
