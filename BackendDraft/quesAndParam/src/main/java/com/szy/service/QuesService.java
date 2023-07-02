package com.szy.service;

import com.szy.pojo.QuestionPageBean;
import com.szy.pojo.Question;


public interface QuesService {

    //list all questions information
//    List<Question> list();

    //delete question by id
    void deleteById(Integer id);

    //add new question
    void add(Question question);

    //select question by id
    Question selectById(Integer id);

    //update question
    void updateQuestion(Question question);

    QuestionPageBean pageQuery(Integer pageNum, Integer pageSize);
}
