package net.lab1024.sa.admin.module.smartWatch.question.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.smartWatch.dao.question.QuesMapper;
import net.lab1024.sa.admin.module.smartWatch.dao.question.QuestionDAO;
import net.lab1024.sa.admin.module.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.smartWatch.question.pojo.QuestionQueryForm;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import java.util.List;

@Service
public class QuesServiceImpl implements QuesService{

    @Autowired
    private QuesMapper quesMapper;
    @Autowired
    private QuestionDAO quesDAO;

    @Transactional
    @Override
    public ResponseDTO<String> deleteById(Long id) {
        quesMapper.deleteById(id);
        quesMapper.changeId(id);
        long count = quesMapper.count();
        quesMapper.resetAutoIncrement(count+1);
        for(long i = id; i < count+1; i++){
            Question question = quesMapper.selectById(i);
            question.setQuestionId(String.format("%05d", question.getId()*1000));
            question.setAnswer1Id(String.format("%05d", question.getId()*1000+10));
            question.setAnswer2Id(String.format("%05d", question.getId()*1000+20));
            question.setAnswer3Id(String.format("%05d", question.getId()*1000+30));
            question.setAnswer4Id(String.format("%05d", question.getId()*1000+40));
            quesMapper.updateAnswerId(question);
        }
        return ResponseDTO.ok();
    }

    @Transactional
    @Override
    public ResponseDTO<String> add(Question question) {
//        long count = quesMapper.count()+1;
        quesMapper.add1(question);
        long id = quesMapper.findLatestId();
        question.setId(id);
        question.setQuestionId(String.format("%05d", id*1000));
        question.setAnswer1Id(String.format("%05d", id*1000+10));
        question.setAnswer2Id(String.format("%05d", id*1000+20));
        question.setAnswer3Id(String.format("%05d", id*1000+30));
        question.setAnswer4Id(String.format("%05d", id*1000+40));
        quesMapper.add2(question);
        return ResponseDTO.ok();
    }

    @Override
    public Question selectById(Long id) {
        return quesMapper.selectById(id);
    }

    @Override
    public ResponseDTO<String> updateQuestion(Question question) {

        quesMapper.updateQuestion(question);
        return ResponseDTO.ok();
    }

    @Transactional
    @Override
    public PageResult<Question> queryPage(QuestionQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<Question> list = quesDAO.queryPage(page, queryForm);
        PageResult<Question> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    public synchronized ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return ResponseDTO.ok();
        }
        for(Long id:idList){
            quesMapper.deleteById(id);
        }
        return ResponseDTO.ok();
    }

}
