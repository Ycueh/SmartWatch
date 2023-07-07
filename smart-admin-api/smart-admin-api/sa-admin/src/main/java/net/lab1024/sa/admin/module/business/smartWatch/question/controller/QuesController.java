package net.lab1024.sa.admin.module.business.smartWatch.question.controller;



//import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.Question;
import net.lab1024.sa.admin.module.business.smartWatch.question.pojo.QuestionPageBean;
import net.lab1024.sa.admin.module.business.smartWatch.Result;
import net.lab1024.sa.admin.module.business.smartWatch.question.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/questions")
@Api(tags = {AdminSwaggerTagConst.Business.SW_QUES})
public class QuesController {
    @Autowired
    private QuesService quesService;

    /**
     * list all questions by page      页码未指定默认为1 每页记录数默认为10
     * @return
     */

    @GetMapping
    public Result pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        QuestionPageBean questionPageBean = quesService.pageQuery(pageNum, pageSize);
        return Result.success(questionPageBean);
    }

    /**
     * delete question by id
     * @param id
     * @return Result response
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        quesService.deleteById(id);
        return Result.success();
    }

    /**
     * add new question
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Question question){
        log.info("add new question");
        quesService.add(question);
        return Result.success();
    }

    /**
     * select question by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Question question = quesService.selectById(id);
        return Result.success(question);
    }

    /**
     * update the question
     * @param question
     * @return
     */
    @PutMapping
    public Result updateQuestion(@RequestBody Question question){
        quesService.updateQuestion(question);
        return Result.success();
    }
}
