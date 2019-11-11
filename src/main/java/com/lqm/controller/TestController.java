package com.lqm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HAL on 2019/11/8.
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

}
