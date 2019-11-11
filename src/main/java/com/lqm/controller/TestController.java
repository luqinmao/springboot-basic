package com.lqm.controller;

import com.lqm.dao.mapper.UmsAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HAL on 2019/11/8.
 */
@RestController
public class TestController {

    @Autowired
    private UmsAdminMapper adminMapper;

    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

    @RequestMapping("/test2")
    public Object test2(){
        return adminMapper.selectByExample(null);
    }
}
