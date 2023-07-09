package net.lab1024.sa.admin.module.business.smartWatch.question.service;


import net.lab1024.sa.admin.module.business.smartWatch.question.mapper.QuesMapper;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.QuestionPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuesServiceImpl implements QuesService{

    @Autowired
    private QuesMapper quesMapper;

//    @Override
//    public List<Question> list(){
//        return quesMapper.list();
//    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        quesMapper.deleteById(id);
        quesMapper.changeId(id);
        long count = quesMapper.count();
        quesMapper.resetAutoIncrement(count+1);
        for(int i = id; i < count+1; i++){
            Question question = quesMapper.selectById(i);
            question.setQuestionId(String.format("%05d", question.getId()*1000));
            question.setAnswer1Id(String.format("%05d", question.getId()*1000+10));
            question.setAnswer2Id(String.format("%05d", question.getId()*1000+20));
            question.setAnswer3Id(String.format("%05d", question.getId()*1000+30));
            question.setAnswer4Id(String.format("%05d", question.getId()*1000+40));
            quesMapper.updateAnswerId(question);
        }

    }

    @Transactional
    @Override
    public void add(Question question) {
        long count = quesMapper.count()+1;
        question.setQuestionId(String.format("%05d", count*1000));
        question.setAnswer1Id(String.format("%05d", count*1000+10));
        question.setAnswer2Id(String.format("%05d", count*1000+20));
        question.setAnswer3Id(String.format("%05d", count*1000+30));
        question.setAnswer4Id(String.format("%05d", count*1000+40));
        quesMapper.add(question);
    }

    @Override
    public Question selectById(Integer id) {
        return quesMapper.selectById(id);
    }

    @Override
    public void updateQuestion(Question question) {
        quesMapper.updateQuestion(question);
    }

    @Transactional
    @Override
    public QuestionPageBean pageQuery(Integer pageNum, Integer pageSize) {
        //get total question number
        Long count = quesMapper.count();

        //get page query list data
        List<Question> questionList = quesMapper.page((pageNum-1)*pageSize , pageSize);

        QuestionPageBean questionPageBean = new QuestionPageBean(count, questionList);
        return questionPageBean;
    }


}
