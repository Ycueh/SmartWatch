package net.lab1024.sa.admin.module.business.smartWatch.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.QuestionQueryForm;
import net.lab1024.sa.admin.module.business.smartWatch.response.domain.responseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//use for page query
@Mapper
@Component
public interface QuestionDAO extends BaseMapper<responseEntity> {

    /**
     * 分页 查询商品
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<Question> queryPage(Page page, @Param("queryForm") QuestionQueryForm queryForm);

}