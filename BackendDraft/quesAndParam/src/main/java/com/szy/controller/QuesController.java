package com.szy.controller;


import com.szy.pojo.QuestionPageBean;
import com.szy.pojo.Question;
import com.szy.pojo.Result;
import com.szy.service.QuesService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/questions")
public class QuesController {
    @Autowired
    private QuesService quesService;

    /**
     * list all questions by page      页码未指定默认为1 每页记录数默认为10
     * @return
     */
    @Operation(summary = "list questions by page 分页查询 前端传2个参数，一个页码，一个每页的数据数。如果前端" +
            "没有传送数据 默认为 1 和 10")
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
    @Operation(summary = "delete question by id   前端传过来一个id")
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
    @Operation(summary = "add new question  自动增加在最后   前端传除了id以外的数据值")
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
    @Operation(summary = "select question by id  前端传入一个id 后端回传给前端此id对应的question数据")
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
    @Operation(summary = "update the question 更改问题的流程是 前端点击编辑按钮后，发送给后端一个id，后端先根据id" +
            "找到指定question（即先使用selectById），然后后端会发送question数据给前端，前端修改后发送给后端，再使用这个接口")
    @PutMapping
    public Result updateQuestion(@RequestBody Question question){
        quesService.updateQuestion(question);
        return Result.success();
    }
}
