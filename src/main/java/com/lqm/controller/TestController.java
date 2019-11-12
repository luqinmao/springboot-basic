package com.lqm.controller;

import com.lqm.dao.mapper.UmsAdminMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation("测试接口2")
    @RequestMapping(value = "/test2" ,method = RequestMethod.GET)
    public Object test2(@RequestParam String param){
        return adminMapper.selectByExample(null);
    }


}
