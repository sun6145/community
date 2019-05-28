package pers.sfl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.User;

/**
 * 用户服务层 解决退出重新登录后重新生成一条记录的问题
 *
 * @author 富良/SCott fl_6145@163.com
 * @create 2019-05-28 14:09
 */
@Service
public class UserService {
  @Autowired private UserMapper userMapper;

  public void updateOrCreate(User user) {
    Integer count = userMapper.findbyAccountId(user.getAccountId());
    if (count > 0) {
      // exist
      user.setGmtModified(System.currentTimeMillis());
      userMapper.updateUser(user);
    } else {
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userMapper.insertUser(user);
    }
  }
}
