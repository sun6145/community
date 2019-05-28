package pers.sfl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.sfl.model.User;

/**
 * 用户mapper
 *
 * @author Scott fl_6145@163.com
 * @create 2019-05-25 17:58
 */
@Mapper
public interface UserMapper {

  @Insert(
      "insert into user (name,account_id,token,gmt_modified,gmt_create,avatar_url) values(#{name},#{accountId},#{token},#{gmtModified},#{gmtCreate},#{avatarUrl})")
  public void insertUser(User user);

  @Select("select * from user where token=#{token} ")
  User findByToken(String token);

  @Select("select * from user where id =#{id}")
  User findByID(Integer id);

  @Select("select count(*) from  user where ACCOUNT_ID =#{accountId}")
  Integer findbyAccountId(String accountId);

  @Update(
      "update user set name=#{name},  token  =#{token },   gmt_modified  =#{gmtModified },  avatar_url  =#{avatarUrl}")
  Integer updateUser(User user);
}
