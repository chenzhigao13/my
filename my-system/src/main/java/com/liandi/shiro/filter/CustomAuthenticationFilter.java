package com.liandi.shiro.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.google.gson.Gson;
import com.liandi.response.ResponseEnum;
import com.liandi.response.dto.ResponseDTO;

/**
 * 认证过滤器
 * 
 * @author czg
 * @date 2019/9/21 16:39
 */
public class CustomAuthenticationFilter extends FormAuthenticationFilter {

    public CustomAuthenticationFilter() {}

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // Always return true if the request's method is OPTIONS
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest)request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write(new Gson().toJson(new ResponseDTO<>(ResponseEnum.SHIRO_NOT_LOGIN_ERROR)));
        writer.close();
        return false;
    }

}
