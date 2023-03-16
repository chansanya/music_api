package com.music.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: IndexController
 * @author: leihuangyan
 * @classPath: com.music.controller.IndexController
 * @date: 2023/3/14
 * @description:
 */
@RestController
@RequestMapping("/")
public class IndexController {



    @GetMapping("/")
    public String index(){
        return "welcome to springboot";
    }


}
