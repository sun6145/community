package pers.sfl.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sfl.dto.AccessTokenDTO;
import pers.sfl.dto.GithubUser;
import pers.sfl.provider.GithubProvider;

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
    public String  getGithubCode(@RequestParam("code") String code, @RequestParam(value = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        System.out.println(githubUser.getName());
        return "index";
    }
}
