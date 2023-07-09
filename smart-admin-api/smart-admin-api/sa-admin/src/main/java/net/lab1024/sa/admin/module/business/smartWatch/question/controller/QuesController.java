package net.lab1024.sa.admin.module.business.smartWatch.question.controller;



//import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.QuestionQueryForm;
import net.lab1024.sa.admin.module.business.smartWatch.question.service.QuesService;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_QUES})
public class QuesController {
    @Autowired
    private QuesService quesService;

    @ApiOperation("queryPage")
    @PostMapping("/question/queryPage")
    public ResponseDTO<PageResult<Question>> queryPage(@RequestBody @Valid QuestionQueryForm queryForm) {
        return ResponseDTO.ok(quesService.queryPage(queryForm));
    }

    /**
     * delete question by id
     * @param
     * @return Result response
     */
    @ApiOperation("Delete question")
    @GetMapping("/question/delete/{questionId}")
    public ResponseDTO<String> delete(@PathVariable("questionId") Long questionId) {
        return quesService.deleteById(questionId);
    }

    /**
     * add new question
     * @return
     */
    @ApiOperation("Add question")
    @PostMapping("/question/add")
    public ResponseDTO<String> add(@RequestBody @Valid Question question) {
        return quesService.add(question);
    }


    /**
     * select question by id
     * @param id
     * @return
     */
    @ApiOperation("get question by id")
    @GetMapping("question/{id}")
    public ResponseDTO<Question> selectById(@PathVariable Long id){
        Question question = quesService.selectById(id);
        return ResponseDTO.ok(question);
    }

    /**
     * update the question
     * @param question
     * @return
     */
    @ApiOperation("Update question")
    @PostMapping("/question/update")
    public ResponseDTO<String> update(@RequestBody @Valid Question question) {
        return quesService.updateQuestion(question);
    }
}
