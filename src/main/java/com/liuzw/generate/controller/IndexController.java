package com.liuzw.generate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转到首页
 *
 * @author liuzw
 * @date 2018/8/1 15:42
 **/
@Controller
@RequestMapping
public class IndexController {

    /**
     * 跳转到 首页 页面
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 跳转到默认 页面
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }


}
