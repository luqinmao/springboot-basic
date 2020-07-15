package com.lqm.controller;


import com.lqm.common.api.CommonConstant;
import com.lqm.model.common.PageBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * BaseController
 * @author caoxuan
 * @date 2019/10/03
 */
public class BaseController {


    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取当前请求
     */
    public HttpServletRequest getRequest() {
        return request;
    }


    /**
     * 从请求头获取token
     * @return
     */
    public String getToken() {
        return request.getHeader(CommonConstant.TOKEN);
    }

    /**
     * 获取page分页参数
     * @return
     */
    public PageBean getPageInFo() {
        PageBean pageBean = new PageBean();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        if (TextUtils.isEmpty(pageNum)){
            pageBean.setPageNum(1);
        }else if (Integer.parseInt(pageNum) == 0){
            pageBean.setPageNum(1);
        }else {
            pageBean.setPageNum(Integer.parseInt(pageNum));
        }

        if (TextUtils.isEmpty(pageSize)){
            pageBean.setPageSize(10);
        }else {
            pageBean.setPageSize(Integer.parseInt(pageSize));
        }

        return pageBean;
    }


    /**
     * 从请求头获取用户类型
     *
     * @param request
     * @return
     */
    public String getUserType(HttpServletRequest request) {
        return request.getHeader(CommonConstant.USER_TYPE);
    }


    /**
     * 从请求头获取当前设备类型
     *
     * @param request
     * @return
     */
    public String getUserAgent(HttpServletRequest request) {
        return request.getHeader(CommonConstant.USER_AGENT);
    }
    /**
     * 获取当前会话
     *
     * @param isNew true:session不存在则创建
     * @return
     */
    public HttpSession getSession(boolean isNew) {
        if (request != null) {
            return request.getSession(isNew);
        }
        return null;
    }

    /**
     * 使用指定的名称将对象绑定到该会话
     *
     * @param key   Name of session
     * @param value Value of session
     */
    public void setSession(String key, String value) {
        getSession(true).setAttribute(key, value);
    }

    /**
     * 获取项目相对路径
     */
    public String getProjectPath() {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * 获取项目物理路径
     */
    public String getProjectMirPath() {
        return getSession(true).getServletContext().getRealPath("/");
    }

    /**
     * 获取访问客户端IP
     */
    public String getClientIP() {
        if (request == null) {
            return null;
        }
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ipAddress;
    }

    public String getRequestParam(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader()) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}