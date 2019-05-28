package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.sfl.dto.QuestionDTO;
import pers.sfl.service.QuestionService;

/**
 * 问题详情
 *
 * @author Scott ： fl_6145@163.com
 * @create 2019-05-28 11:02
 */
@Controller
public class QuestionController {
  @Autowired private QuestionService questionService;

  @GetMapping(value = "/question/{id}")
  public String question(@PathVariable("id") Integer id, Model model) {
    QuestionDTO questionDTO = questionService.getQuestionById(id);
    model.addAttribute("questionDTO", questionDTO);
    return "question";
  }
}
