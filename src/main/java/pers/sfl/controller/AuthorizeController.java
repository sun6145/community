package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sfl.dto.AccessTokenDTO;
import pers.sfl.dto.GithubUser;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.User;
import pers.sfl.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * github授权
 *
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 21:59
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;


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
    public String getGithubCode(@RequestParam("code") String code, @RequestParam(value = "state") String state, HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        //System.out.println(githubUser.getName());
        if (githubUser != null) {
            User user = new User();
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(githubUser.getId() + "");
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
            //登录成功
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
