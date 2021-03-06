package pers.sfl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Scott fl_6145@163.com
 * @create 2019-05-27 18:06
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

  @Autowired private UserMapper userMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 保证有cookie的用户自动登录放到session
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("token")) {
          String token = cookie.getValue();
          User user = userMapper.findByToken(token);
          if (user != null
              && !StringUtils.isEmpty(user.getAccountId())
              && user.getAccountId() != "0"
              && !StringUtils.isEmpty(user.getName())) {
            request.getSession().setAttribute("user", user);
          }
          break;
        }
      }
    }
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {}
}
