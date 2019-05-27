package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;
import pers.sfl.mapper.QuestionMapper;
import pers.sfl.model.Question;
import pers.sfl.model.User;

import javax.servlet.http.HttpSession;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-26 14:17
 */
@Controller
public class publishController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户没有登陆");
            return "redirect:/";
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            Model model, HttpSession session) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录。");
            return "publish";
        }
        Question question = new Question();
        question.setDescription(description);
        question.setTag(tag);
        question.setTitle(title);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        question.setCommentCount(0);
        question.setViewCount(0);
        question.setLikeCount(0);
        questionMapper.create(question);
        return "redirect:/";
    }
}
