package net.lab1024.sa.admin.module.smartWatch.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.smartWatch.question.pojo.QuestionQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

//use for page query
@Mapper
@Component
public interface QuestionDAO extends BaseMapper<ResponseEntity> {

    /**
     * query by pages
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<Question> queryPage(Page page, @Param("queryForm") QuestionQueryForm queryForm);

}
