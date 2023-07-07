package net.lab1024.sa.admin.module.business.smartWatch.question.pojo;

// the entity of Question

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @Column(name = "questionID")
    private String questionId;

//    @Column(name = "question")
    private String question;

    @Column(name = "answer1ID")
    private String answer1Id;

//    @Column(name = "answer1", columnDefinition = "TEXT DEFAULT 'NULL'")
    private String answer1;

    @Column(name = "answer2ID")
    private String answer2Id;

//    @Column(name = "answer2", columnDefinition = "TEXT DEFAULT 'NULL'")
    private String answer2;

    @Column(name = "answer3ID")
    private String answer3Id;

//    @Column(name = "answer3", columnDefinition = "TEXT DEFAULT 'NULL'")
    private String answer3;

    @Column(name = "answer4ID")
    private String answer4Id;

//    @Column(name = "answer4", nullable = true, columnDefinition = "TEXT DEFAULT 'NULL'")
    private String answer4;

    public String getAnswer1() {
        return (answer1 != null) ? answer1 : "NULL";
    }

    public String getAnswer2() {
        return (answer2 != null) ? answer2 : "NULL";
    }

    public String getAnswer3() {
        return (answer3 != null) ? answer3 : "NULL";
    }

    public String getAnswer4() {
        return (answer4 != null) ? answer4 : "NULL";
    }
}
