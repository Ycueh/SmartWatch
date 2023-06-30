package com.szy.pojo;

// the entity of Question

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @Column(name = "questionID")
    private String questionId;

    @Column(name = "question")
    private String question;

    @Column(name = "answer1ID")
    private String answer1Id;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2ID")
    private String answer2Id;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3ID")
    private String answer3Id;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "answer4ID")
    private String answer4Id;

    @Column(name = "answer4")
    private String answer4;
}
