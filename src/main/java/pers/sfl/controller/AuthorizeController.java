package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sfl.dto.AccessTokenDTO;
import pers.sfl.dto.GithubUser;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.User;
import pers.sfl.provider.GithubProvider;
import pers.sfl.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * github授权
 *
 * @author Scott fl_6145@163.com
 * @create 2019-05-24 21:59
 */
@Controller
public class AuthorizeController {

  @Autowired private GithubProvider githubProvider;

  @Autowired private UserMapper userMapper;

  @Autowired private UserService userService;

  @Value("${github.client_id}")
  private String client_id;

  @Value("${github.client_secret}")
  private String client_secret;

  @Value("${github.redirect_uri}")
  private String redirect_uri;

  /**
   * GitHub将用户重定向回您的网站
   *
   * @param code
   * @param state
   */
  @RequestMapping("/callback")
  public String getGithubCode(
      @RequestParam("code") String code,
      @RequestParam(value = "state") String state,
      HttpServletRequest request,
      HttpServletResponse response,
      Model model) {
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_secret(client_secret);
    accessTokenDTO.setClient_id(client_id);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setState(state);
    accessTokenDTO.setRedirect_uri(redirect_uri);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.getGithubUser(accessToken);
    // System.out.println(githubUser.getName());
    if (githubUser != null) {
      User user = new User();
      user.setName(githubUser.getName());
      String token = UUID.randomUUID().toString();
      user.setToken(token);
      user.setAccountId(githubUser.getId() + "");
      user.setAvatarUrl(githubUser.getAvatar_url());
      String info = userService.updateOrCreate(user);
      if (!info.equals("error")) {
        // 添加一个cookie
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(3600); // 设置cookie过期时间为1个小时
        response.addCookie(cookie);
        // 登录成功
        request.getSession().setAttribute("user", user);
        return "redirect:/";
      } else if (info.equals("error")) {
        model.addAttribute("msg", "系统更换了client_secret，请联系管理员解决：XXXX@XXXX.com");
        return "index";
      }
    }
    // 登录失败，重新登录
    return "redirect:/";
  }

  @RequestMapping("logout")
  public String logout(HttpSession session, HttpServletResponse response) {
    // cleanUp The Session
    session.removeAttribute("user");
    // Clean up the cookie
    Cookie cookie = new Cookie("token", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/";
  }
}
