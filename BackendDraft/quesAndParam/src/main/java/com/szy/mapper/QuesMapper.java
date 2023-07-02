package com.szy.mapper;

import com.szy.pojo.Question;

import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface QuesMapper {
    //list all question information
//    @Select("select * from Question")
//    List<Question> list();

    //get the total question number
    @Select("select count(*) from Question")
    public Long count();

    //paging queries   get the list data
    @Select("select * from Question limit #{start}, #{pageSize}")
    public List<Question> page(Integer start, Integer pageSize);

    //delete question by id
    @Delete("delete from Question where _id = #{id}")
    void deleteById(Integer id);

    //update the id to be continued
    @Update("UPDATE Question SET _id = _id - 1 WHERE _id > #{id}")
    void changeId(Integer id);

    //update the auto-increment number
    @Update("ALTER TABLE Question AUTO_INCREMENT = #{count}")
    void resetAutoIncrement(Long count);

    //add new question
    @Insert("insert into " +
            "Question" +
            "(questionID, question, answer1ID, answer1, answer2ID, answer2, answer3ID, answer3, answer4ID, answer4) " +
            "values " +
            "(#{questionId}, #{question}, #{answer1Id}, #{answer1}, #{answer2Id}, #{answer2}, #{answer3Id}, #{answer3}, #{answer4Id}, #{answer4})")
    void add(Question question);

    //select question by id
    @Select("select * from Question where _id = #{id}")
    Question selectById(Integer id);

    //update question
    @Update("update " +
            "Question " +
            "set " +
            "question = #{question}, answer1 = #{answer1}, answer2 = #{answer2}, answer3 = #{answer3}, answer4 = #{answer4} "+
            "where _id = #{id}")
    void updateQuestion(Question question);

    //update answer
    @Update("update " +
            "Question " +
            "set " +
            "questionID = #{questionId}, answer1ID = #{answer1Id}, answer2ID = #{answer2Id}, answer3ID = #{answer3Id}, answer4ID = #{answer4Id} "+
            "where _id = #{id}")
    void updateAnswerId(Question question);

}
