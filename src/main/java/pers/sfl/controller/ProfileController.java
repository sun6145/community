package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sfl.dto.PaginationDTO;
import pers.sfl.model.User;
import pers.sfl.service.QuestionService;

import javax.servlet.http.HttpSession;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-27 14:34
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("profile/{action}")
    public String profile(@PathVariable(name="action") String action,
                          Model model,
                          HttpSession session,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size){
        User user = (User) session.getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户没有登陆");
            return "redirect:/";
        }
        switch (action){
            case "questions":
                model.addAttribute("section","questions");
                model.addAttribute("sectionName","我的提问");
                PaginationDTO questionByUser =  questionService.list(user.getId(), page, size);
                    model.addAttribute("questionByUser",questionByUser);
                break;
            case "replies":
                model.addAttribute("section","replies");
                model.addAttribute("sectionName","最新回复");
                break;
        }
        return "profile";

    }

}
