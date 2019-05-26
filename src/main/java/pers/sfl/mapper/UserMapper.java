package pers.sfl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.sfl.model.User;

/**
 * 用户mapper
 *
 * @author Scott  fl_6145@163.com
 * @create 2019-05-25 17:58
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_modified,gmt_create) values(#{name},#{accountId},#{token},#{gmtModified},#{gmtCreate})")
    public void insertUser(User user);

    @Select("select * from user where token=#{token} ")
    User findByToken(String token);
}
