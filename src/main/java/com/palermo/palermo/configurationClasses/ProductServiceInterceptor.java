/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.configurationClasses;

import com.palermo.palermo.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Los_e
 */
@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        if (!request.getRequestURI().contains("index")
//                || !request.getRequestURI().contains("register")                
//                || !request.getRequestURI().contains("css")                
//                || !request.getRequestURI().contains("js")                
//                || !request.getRequestURI().contains("images")                
//                || !request.getRequestURI().equals("/palermo/")
//                ) {
        if (!request.getRequestURI().startsWith("/palermo/index")
                && !request.getRequestURI().startsWith("/palermo/register")
                && !request.getRequestURI().startsWith("/palermo/css")
                && !request.getRequestURI().startsWith("/palermo/js")
                && !request.getRequestURI().startsWith("/palermo/images")
                && !request.getRequestURI().equals("/palermo/")
                && !request.getRequestURI().equals("/palermo/error/inactiveaccount")
                && !request.getRequestURI().contains("jquery")
                && !request.getRequestURI().contains("bootstrapcdn")
                ) {

            if (request.getSession().getAttribute("loggedinuser") == null) {
                response.sendRedirect("/palermo/index/login");
                return false;
            } else {
                User user = (User) request.getSession().getAttribute("loggedinuser");
                if (user.getActive() == 0) {
                    response.sendRedirect("/palermo/error/inactiveaccount");
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, @Nullable ModelAndView mav) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, @Nullable Exception excptn) throws Exception {
    }
}
