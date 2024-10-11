package com.wo.authservice.util;

import com.wo.authservice.web.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static final String REGISTRATION = "Registration ";
    public static final String DELETION = "Deletion ";
    public static final String SUCCESSFUL = "Successful";
    public static final String FAILED = "Failed";

    private static CustomUserDetails currentUserDetailsInternal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails;
        }
        return null;
    }

    public static Long currentAuthenticatedUserId() {
        CustomUserDetails userDetails = currentUserDetailsInternal();
        return userDetails != null ? userDetails.getId() : null;
    }

    public static CustomUserDetails currentUserDetails() {
        return currentUserDetailsInternal();
    }

}
