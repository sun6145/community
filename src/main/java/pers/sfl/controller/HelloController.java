package pers.sfl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 15:06
 */
@Controller
public class HelloController {
    @GetMapping(value = "/")
    public String hello() {
        return "index";
    }
}
