package pers.sfl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.sfl.dto.PaginationDTO;
import pers.sfl.mapper.UserMapper;
import pers.sfl.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 15:06
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/")
    public String hello( HttpServletRequest request,
        Model model,
        @RequestParam(name="page",defaultValue = "1") Integer page,
        @RequestParam(name="size",defaultValue = "5") Integer size
        ) {
        PaginationDTO paginationDTO = questionService.list(page, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }


}
