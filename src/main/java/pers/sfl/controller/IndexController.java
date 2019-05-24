package pers.sfl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 15:06
 */
@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String hello() {
        return "index";
    }

}
