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
@EntityListeners(eventService.class)
public class EventEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "_id")
    private Long id;

    @Column(name = "eventdate")
    LocalDate eventdate;

    @Column(name = "eventtime")
    LocalTime eventtime;

    @Column(name = "eventdesc")
    private String eventdesc;

    @Column(name = "eventid")
    private Long eventid;

    @Column(name = "eventtype")
    private String eventtype;

    @PrePersist
    public void prePersist(Object o) {
        eventdate = LocalDate.now();
        eventtime = LocalTime.now();
    }
}
