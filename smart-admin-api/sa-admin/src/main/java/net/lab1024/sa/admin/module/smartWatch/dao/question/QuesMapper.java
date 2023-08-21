package net.lab1024.sa.admin.module.smartWatch.dao.question;

import net.lab1024.sa.admin.module.smartWatch.question.pojo.Question;
import org.apache.ibatis.annotations.*;

@Mapper
public interface QuesMapper {
    //get the total question number
    @Select("select count(*) from question")
    public Long count();

    //delete question by id
    @Delete("delete from question where _id = #{id}")
    void deleteById(Long id);

//    update the id to be continued
    @Update("UPDATE question SET _id = _id - 1 WHERE _id > #{id}")
    void changeId(Long id);

//    update the auto-increment number
    @Update("ALTER TABLE question AUTO_INCREMENT = #{count}")
    void resetAutoIncrement(Long count);

//    add new question
    @Insert("insert into " +
            "question" +
            "(questionID, question, answer1ID, answer1, answer2ID, answer2, answer3ID, answer3, answer4ID, answer4) " +
            "values " +
            "(#{questionId}, #{question}, #{answer1Id}, #{answer1}, #{answer2Id}, #{answer2}, #{answer3Id}, #{answer3}, #{answer4Id}, #{answer4})")
    void add(Question question);

    //select question by id
    @Select("select * from question where _id = #{id}")
    Question selectById(Long id);

    //update question
    @Update("update " +
            "question " +
            "set " +
            "question = #{question}, answer1 = #{answer1}, answer2 = #{answer2}, answer3 = #{answer3}, answer4 = #{answer4} "+
            "where _id = #{id}")
    void updateQuestion(Question question);

    //update answer
    @Update("update " +
            "question " +
            "set " +
            "questionID = #{questionId}, answer1ID = #{answer1Id}, answer2ID = #{answer2Id}, answer3ID = #{answer3Id}, answer4ID = #{answer4Id} "+
            "where _id = #{id}")
    void updateAnswerId(Question question);

}
