package net.lab1024.sa.admin.module.smartWatch.response.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;

@Data
@TableName("response")
public class ResponseEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name="_id")
    private Long id;

    /**
     * Response Date
     */
    private String date;

    /**
     * Response Time
     */
    private String time;

    /**
     * Response Time
     */
    @Column(name="responseTime")
    private String responsetime;

    /**
     * question ID
     */
    @Column(name="questionID")
    private String questionid;

    /**
     * answer ID
     */
    @Column(name="answerID")
    private String answerid;

    /**
     * question
     */
    private String question;

    /**
     * response
     */
    private String response;
}
