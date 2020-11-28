package com.fyp.prograd.utilities;

import com.fyp.prograd.exceptions.TokenIncorrectException;
import com.fyp.prograd.exceptions.TokenNotProvidedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

    @Value("${token.secret}")
    private String secretToken;

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        switch (request.getRequestURI()) {
            case "/api/students/login":
            case "/api/students/add":
            case "/api/students/update":
            case "/api/students/findByEmail":
            case "/api/students/findByUsername":
            case "/api/students/findByToken":
            case "/api/companies/update":
            case "/api/companies/add":
            case "/api/companies/findByEmail":
            case "/api/companies/findByName":
            case "/api/companies/findByToken":
            case "/api/courses/add":
            case "/api/positions/add":
            case "/api/positions/all":
            case "/api/positions/findById":
                break;
            default:
                return verifyUserToken(request);
        }

        return true;
    }

    private boolean verifyUserToken(HttpServletRequest request) throws TokenNotProvidedException, TokenIncorrectException {
        String authorization = request.getHeader("x-api-key");
        if (authorization == null || !authorization.equals(secretToken)) {
            throw new TokenNotProvidedException();
        }

        return true;
    }

}
