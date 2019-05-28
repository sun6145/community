package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sfl.dto.QuestionDTO;
import pers.sfl.model.Question;
import pers.sfl.model.User;
import pers.sfl.service.QuestionService;

import javax.servlet.http.HttpSession;

/**
 * @author Scott fl_6145@163.com
 * @create 2019-05-26 14:17
 */
@Controller
public class publishController {

  @Autowired private QuestionService questionService;

  @GetMapping("/publish/{id}")
  public String editQuestion(@PathVariable("id") Integer id, Model model) {
    QuestionDTO question = questionService.getQuestionById(id);
    model.addAttribute("title", question.getTitle());
    model.addAttribute("description", question.getDescription());
    model.addAttribute("tag", question.getTag());
    model.addAttribute("id", id);
    return "/publish";
  }

  @GetMapping("/publish")
  public String publish(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("error", "用户没有登陆");
      return "redirect:/";
    }
    return "publish";
  }

  @PostMapping("/publish")
  public String doPublish(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "tag", required = false) String tag,
      @RequestParam(value = "id") Integer id,
      Model model,
      HttpSession session) {

    model.addAttribute("title", title);
    model.addAttribute("description", description);
    model.addAttribute("tag", tag);
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
    question.setCreator(user.getId());
    questionService.createOrUpdate(question, id);
    return "redirect:/";
  }
}
