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

@Order(2)
@Component
public class RoleFilter implements Filter {

    private TokenService tokenService;
    private AccountRepository repository;

    public RoleFilter(TokenService tokenService, AccountRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        String method = request.getMethod();
        String token = request.getHeader("Authorization");
        if(path.startsWith("/payment/all") && "DELETE".equals(method) && token != null){
            AccountCredentials credentials = tokenService.decodeToken(token);
            AccountEntity account = repository.findUserByEmail(credentials.email);
            if(account.role != AccountEntity.ROLE.ADMIN){
                response.sendError(401,"Permission denied");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
