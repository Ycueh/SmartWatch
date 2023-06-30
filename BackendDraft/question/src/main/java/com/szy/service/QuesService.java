package com.szy.service;

import com.szy.pojo.PageBean;
import com.szy.pojo.Question;

import java.util.List;


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

    PageBean pageQuery(Integer pageNum, Integer pageSize);
}
