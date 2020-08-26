package com.project.MyStickyNotes.Filters;

import com.project.MyStickyNotes.Util.JsonResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class cookiecheckfilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {


        String path = servletRequest.getServletPath();

        if (!path.contains("/login")) {
            chain.doFilter(servletRequest, servletResponse);

        }
        //if path starts with login
        else {
            // check for the cookies
            Cookie cookie[] = servletRequest.getCookies();
            if (cookie == null) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                for (Cookie c : cookie) {
                    if (c.getName().equals("remember-me")) {


                        servletResponse.setContentType("application/json");
                        servletResponse.getWriter().print(new JsonResponse("autologin", true, null, null));
                    }
                }
            }
        }
    }


}
