package com.szy.service;

import com.szy.mapper.QuesMapper;
import com.szy.pojo.PageBean;
import com.szy.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuesServiceImpl implements QuesService{

    @Autowired
    private QuesMapper quesMapper;

//    @Override
//    public List<Question> list(){
//        return quesMapper.list();
//    }

    @Override
    public void deleteById(Integer id) {
       quesMapper.deleteById(id);
    }

    @Override
    public void add(Question question) {

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

    @Override
    public PageBean pageQuery(Integer pageNum, Integer pageSize) {
        //get total question number
        Long count = quesMapper.count();

        //get page query list data
        List<Question> questionList = quesMapper.page((pageNum-1)*pageSize , pageSize);

        PageBean pageBean = new PageBean(count, questionList);
        return pageBean;
    }


}
