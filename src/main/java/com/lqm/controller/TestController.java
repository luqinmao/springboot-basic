package com.lqm.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.lqm.dao.mapper.UmsAdminMapper;
import com.lqm.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "TestController", tags = "测试用例")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminService umsAdminService;


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
    public Object test2(@RequestParam(required = true) String param){
        if (true){
            throw new RuntimeException("异常抛出信息1");
        }
        return adminMapper.selectByExample(null);
    }

    @ApiOperation("测试接口3")
    @RequestMapping(value = "/test3" ,method = RequestMethod.GET)
    public Object test3(){
        umsAdminService.getAdminByUsername("");

        String content = HttpUtil.get("http://www.baidu.com");
        MapUtil.builder().put("","").build();
        SecureUtil.md5("123123123");
        return content;
    }

}
