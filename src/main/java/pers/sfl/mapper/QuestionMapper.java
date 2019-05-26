package pers.sfl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.sfl.model.Question;

import java.util.List;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-26 14:56
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,like_count,tag,view_count,comment_count,creator,gmt_modified,gmt_create) values(#{title},#{description},#{likeCount},#{tag},#{viewCount},#{commentCount},#{creator},#{gmtModified},#{gmtCreate}) ")
    public void  create (Question question );

    @Select("select * from question")
    List<Question> list();
}