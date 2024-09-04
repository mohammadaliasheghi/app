package com.mmad.oauth.config;

import io.jsonwebtoken.Jwts;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {

    public static String getCurrentUsername(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static Long getCurrentId(String token, String secretKey) {
        return Long.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getId());
    }

    public static String getTokenFromCurrentRequest() {
        String authorization;
        ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest currentRequest = currentRequestAttributes.getRequest();
        authorization = currentRequest.getHeader("authorization");
        if (authorization == null) {
            authorization = currentRequest.getHeader("Authorization");
        }
        return authorization;
    }
}
