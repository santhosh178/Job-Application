package com.example.firstproject.security;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private  static  final Logger logger =  LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Responding with unauthorized error . message -{}",authException.getMessage());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("message","UNAUTHORIZED USER");
        json.put("status_code","401");
        writer.println(json);

    }

}
