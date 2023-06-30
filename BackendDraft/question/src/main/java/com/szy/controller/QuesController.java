package com.szy.controller;


import com.szy.pojo.PageBean;
import com.szy.pojo.Question;
import com.szy.pojo.Result;
import com.szy.service.QuesService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/questions")
public class QuesController {
    @Autowired
    private QuesService quesService;

    /**
     * list all questions
     * @return Result response
     */
//    @GetMapping
//    public Result list(){
//        List<Question> questionList = quesService.list();
//        return Result.success(questionList);
//    }

    /**
     * list all questions by page      页码未指定默认为1 每页记录数默认为10
     * @return
     */
    @Operation(summary = "list all questions by page")
    @GetMapping
    public Result pageQuery(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = quesService.pageQuery(pageNum, pageSize);
        return Result.success(pageBean);
    }

    /**
     * delete question by id
     * @param id
     * @return Result response
     */
    @Operation(summary = "delete question by id")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
//        log.info("delete question by id");
        quesService.deleteById(id);
        return Result.success();
    }

    /**
     * add new question
     * @return
     */
    @Operation(summary = "add new question")
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
    @Operation(summary = "select question by id")
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
    @Operation(summary = "update the question")
    @PutMapping
    public Result updateQuestion(@RequestBody Question question){
        quesService.updateQuestion(question);
        return Result.success();
    }
}
