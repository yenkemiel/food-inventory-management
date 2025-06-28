package com.uch.finalproject;

import jakarta.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 這是展示版,直接通過不檢查登入
        chain.doFilter(request, response);
    }
}
