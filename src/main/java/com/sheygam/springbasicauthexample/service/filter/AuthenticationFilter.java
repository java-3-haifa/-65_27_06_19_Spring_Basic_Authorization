package com.sheygam.springbasicauthexample.service.filter;


import com.sheygam.springbasicauthexample.repository.AccountRepository;
import com.sheygam.springbasicauthexample.repository.entity.AccountEntity;
import com.sheygam.springbasicauthexample.service.AccountCredentials;
import com.sheygam.springbasicauthexample.service.TokenService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    private TokenService validationService;
    private AccountRepository repository;

    public AuthenticationFilter(TokenService validationService, AccountRepository repository) {
        this.validationService = validationService;
        this.repository = repository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();

        if (path.startsWith("/payment")) {
            String token = request.getHeader("Authorization");
            if (token == null) {
                response.addHeader("WWW-Authenticate","Basic realm=\"User Visible Realm\"");
                response.sendError(401, "Unauthorized");
                return;
            }
            AccountCredentials userCredentials = null;
            try {
                userCredentials = validationService.decodeToken(token);
            } catch (Exception e) {
                response.sendError(401, "Wrong token format");
                return;
            }
            AccountEntity userAccount = repository.findUserByEmail(userCredentials.email);


            if (userAccount == null) {
                response.sendError(401, "User not found");
                return;
            }

            if(!userCredentials.password.equals(userAccount.password)){
                response.sendError(401,"Wrong email or password");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
