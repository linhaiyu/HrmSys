package com.hr.interceptor;

import com.hr.common.HrmConstants;
import com.hr.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URI = {"/loginForm", "/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;

        String servletPath = request.getServletPath();

        for (String s: IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            User user = (User) request.getSession().getAttribute(HrmConstants.USER_SESSION);

            if (user == null) {
                request.setAttribute("message", "请先登录，谢谢！");
                request.getRequestDispatcher(HrmConstants.LOGIN).forward(request, response);
            } else {
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
