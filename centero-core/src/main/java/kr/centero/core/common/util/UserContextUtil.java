package kr.centero.core.common.util;

import kr.centero.core.common.model.CenteroUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * UserUtils:
 * This class is used to get authenticated user name from the security context.
 */
public class UserContextUtil {
    private UserContextUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getAuthenticatedUserName() {
        String username = "";
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            username = auth != null ? ((CenteroUserDetails) auth.getPrincipal()).getUsername() : null;
            return username;
        } catch (Exception e) {
            return username;
        }
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
