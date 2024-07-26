package com.homepage.localtum.util.Member;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationMemberUtils {
    public String getCurrentMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println((String) authentication.getPrincipal());
        return (String) authentication.getPrincipal();
    }
}
