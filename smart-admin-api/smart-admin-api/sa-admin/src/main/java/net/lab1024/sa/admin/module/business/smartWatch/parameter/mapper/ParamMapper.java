package net.lab1024.sa.admin.module.business.smartWatch.parameter.mapper;


import net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo.Parameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ParamMapper {
    @Select("select * from Parameter where _id = #{id}")
    Parameter selectById(Integer id);

    @Update("update Parameter set paramvalue = #{paramValue} where _id = #{id}")
    void updateParam(Parameter parameter);

//    @Select("select * from Parameter limit #{start}, #{pageSize}")
//    List<Parameter> pageQuery(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from Parameter")
    Long count();
}
