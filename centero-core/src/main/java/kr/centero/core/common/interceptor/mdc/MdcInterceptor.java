package kr.centero.core.common.interceptor.mdc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * MdcInterceptor:
 * This class is used to generate a unique ID for each request.
 * This ID is used to track the request and response logs.
 */
@Component
public class MdcInterceptor implements HandlerInterceptor {
    private static final String TRACE_ID = "TraceID";

    @Override
    public boolean preHandle(@NonNull  HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String authenticatedUserName = UserUtils.getAuthenticatedUserName();
        if (StringUtils.isNotBlank(authenticatedUserName)) {
            MDC.put(TRACE_ID, MdcInterceptor.getRandomString() + "-" + authenticatedUserName);
        } else {
            MDC.put(TRACE_ID, MdcInterceptor.getRandomString());
        }
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        MDC.remove(TRACE_ID);
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
