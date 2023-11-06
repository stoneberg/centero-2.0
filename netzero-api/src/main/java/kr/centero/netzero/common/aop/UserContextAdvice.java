package kr.centero.netzero.common.aop;

import kr.centero.core.common.payload.BaseRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This advice is used to set the user context info(userId, roles) to the base request.
 */
@Aspect
@Component
public class UserContextAdvice {
    @Before("execution(* kr.centero.netzero.*.*.web.*.*(..)) && args(request,..)")
    public void enrichRequestContext(JoinPoint joinPoint, BaseRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && request != null) {
            request.setCenteroUserId(auth.getName());

            List<String> roles = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            request.setCenteroUserRoles(roles);
        }
    }

}

