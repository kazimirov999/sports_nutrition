package net.sports.nutrition.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class for tracking requests after processing and recording URI to session.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class LastModelAndViewInterceptor extends HandlerInterceptorAdapter {

    /**
     * constant storage URI.
     */
    public static final String LAST_URI = "lastUri";

    /**
     * Writes URI to session from request. Called after the handler execution.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (request.getRequestURI() != null && !request.getRequestURI().contains("/photo/")
                && !request.getRequestURI().contains("/styles/") && !request.getRequestURI().contains("null")) {

            request.getSession(true).setAttribute(LAST_URI, request.getRequestURI());
        }

        super.postHandle(request, response, handler, modelAndView);
    }

}