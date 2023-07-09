package net.lab1024.sa.admin.module.business.smartWatch.question.service;


import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.QuestionQueryForm;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;

public interface QuesService {
    //delete question by id
    ResponseDTO<String> deleteById(Long id);

    //add new question
    ResponseDTO<String> add(Question question);

    //select question by id
    Question selectById(Long id);

    //update question
    ResponseDTO<String> updateQuestion(Question question);

    PageResult<Question> queryPage(QuestionQueryForm queryForm);
}
