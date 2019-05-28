package pers.sfl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.sfl.model.Question;

import java.util.List;

/**
 * @author Scott fl_6145@163.com
 * @create 2019-05-26 14:56
 */
@Mapper
public interface QuestionMapper {

  @Insert(
      "insert into question (title,description,like_count,tag,view_count,comment_count,creator,gmt_modified,gmt_create) values(#{title},#{description},#{likeCount},#{tag},#{viewCount},#{commentCount},#{creator},#{gmtModified},#{gmtCreate}) ")
  public void create(Question question);

  @Select("select * from question limit #{offset},#{size} ")
  List<Question> list(Integer offset, Integer size);

  @Select("select count(*) from question")
  Integer getCount();

  @Select("select * from question where creator=#{id} limit #{offset},#{size} ")
  List<Question> listByUser(Integer id, Integer offset, Integer size);

  @Select("select * from question where id=#{id}")
  List<Question> getQuestionById(Integer id);

  @Update(
      "update question set title= #{title},description=#{description},tag=#{tag}, gmt_modified=#{gmtModified}")
  void updateInfo(Question question);
}
