package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 15:06
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }

            }
        }

        return "index";
    }


}
