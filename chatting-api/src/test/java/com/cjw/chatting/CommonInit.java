package com.cjw.chatting;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonInit {
    public static void init() {
        MockHttpSession httpSession = new MockHttpSession();
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
    }
}
