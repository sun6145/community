package pers.sfl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.User;

/**
 * 用户服务层 Resolve the issue of regenerating a record after logging out again
 *
 * @author 富良/SCott fl_6145@163.com
 * @create 2019-05-28 14:09
 */
@Service
public class UserService {
  @Autowired private UserMapper userMapper;

  public String updateOrCreate(User user) {
    Integer count = userMapper.findbyAccountId(user.getAccountId());
    if (count >= 0 && !user.getAccountId().equals("0")) {
      // exist
      user.setGmtModified(System.currentTimeMillis());
      userMapper.updateUser(user);
    } else {
      if (user.getAccountId().equals("0")) {
        return "error";
      } else {
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        userMapper.insertUser(user);
      }
    }
    return "success";
  }
}
