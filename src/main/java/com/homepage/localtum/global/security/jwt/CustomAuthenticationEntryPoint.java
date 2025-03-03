package com.homepage.localtum.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homepage.localtum.util.response.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CustomApiResponse<?> apiResponse = new CustomApiResponse<>(HttpStatus.UNAUTHORIZED.value(), null, "토큰이 필요합니다.", null);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(apiResponse));
    }
}
