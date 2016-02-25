package net.sports_nutrition.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 13:22
 */
public class LastModelAndViewInterceptor extends HandlerInterceptorAdapter {

    public static final String LAST_URI = "lastUri";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(request.getRequestURI() != null && !request.getRequestURI().contains("/photo/")
                &&!request.getRequestURI().contains("/styles/")&&!request.getRequestURI().contains("null")) {
            request.getSession(true).setAttribute(LAST_URI, request.getRequestURI());
            System.out.println("REQURI--------" + request.getRequestURI());
        }

        super.postHandle(request, response, handler, modelAndView);
    }

}