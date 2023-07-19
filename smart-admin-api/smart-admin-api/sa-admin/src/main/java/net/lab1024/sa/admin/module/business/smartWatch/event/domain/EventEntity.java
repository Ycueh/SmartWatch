package net.lab1024.sa.admin.module.business.smartWatch.event.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import net.lab1024.sa.admin.module.business.smartWatch.event.service.eventService;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@TableName("event")
public class EventEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "eventdate")
    String eventdate;

    @Column(name = "eventtime")
    String eventtime;

    @Column(name = "eventdesc")
    private String eventdesc;

    @Column(name = "eventid")
    private Long eventid;

    @Column(name = "eventtype")
    private String eventtype;

}
