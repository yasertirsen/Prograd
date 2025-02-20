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
            case "/api/students/add":
            case "/api/companies/add":
            case "/error":
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
