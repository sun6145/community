package pers.sfl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 15:06
 */
@RestController
public class HelloController {
    @GetMapping(value = "hello")
    public String hello(@RequestParam("name") String name) {
        return "欢迎您：" + name;
    }
}
