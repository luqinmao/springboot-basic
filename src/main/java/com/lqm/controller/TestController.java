package com.lqm.controller;

import com.lqm.dao.mapper.UmsAdminMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "TestController", description = "测试用例")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

    @Autowired
    private UmsAdminMapper adminMapper;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("request.getRequestURL():"+request.getRequestURL() +"\n")
                .append("getToken: "+getToken() +"\n")
                .append("getClientIP: "+getClientIP() +"\n")
                .append("getRequestParam: "+getRequestParam(request) +"\n")
                .append("getPageInFo: "+getPageInFo().toString() +"\n")
        ;
        return stringBuffer.toString();
    }

    @ApiOperation("测试接口2")
    @RequestMapping(value = "/test2" ,method = RequestMethod.GET)
    public Object test2(@RequestParam(required = false) String param){
        return adminMapper.selectByExample(null);
    }


}
