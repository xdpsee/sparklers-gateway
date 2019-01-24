package com.zhenhui.demo.sparklers.gateway.utils;

import com.zhenhui.demo.sparklers.uic.security.Subject;
import com.zhenhui.demo.sparklers.uic.security.domain.SecurityToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SuppressWarnings("unused")
public final class CurrentUser {

    private CurrentUser() {}

    public static Subject get() {

        final SecurityToken authentication = (SecurityToken) SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            return (Subject) authentication.getPrincipal();
        }

        return null;
    }

    public static long getUserId() {
        Subject subject = get();
        if (subject != null) {
            return subject.getUserId();
        }

        return 0L;
    }
}
