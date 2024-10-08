package com.mmad.oauth.config;

import com.mmad.oauth.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    @Value("${security.password.secret-key}")
    private String SECRET_KEY;

    private final JwtConfig jwtConfig;
    private final UsersService usersService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(Constant.AUTHORIZATION);

        if (jwt != null && jwtConfig.validateToken(jwt)) {
            String jwtUsername = SecurityUtil.getCurrentUsername(jwt, SECRET_KEY);
            if (jwtUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails users = usersService.loadUserByUsername(jwtUsername);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
