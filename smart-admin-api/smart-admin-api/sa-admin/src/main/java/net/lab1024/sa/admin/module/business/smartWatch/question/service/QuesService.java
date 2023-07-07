package net.lab1024.sa.admin.module.business.smartWatch.question.service;


import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.QuestionPageBean;

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
