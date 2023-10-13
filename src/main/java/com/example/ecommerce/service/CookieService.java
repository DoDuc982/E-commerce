package com.example.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieService {

    // Thời gian sống mặc định của cookie (ở đây là 30 ngày)
    private static final int COOKIE_MAX_AGE = 30 * 24 * 60 * 60;

    // Lấy giá trị của cookie dựa trên tên
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Tạo một cookie mới
    public void addCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(COOKIE_MAX_AGE); // Thời gian sống của cookie
        cookie.setPath("/"); // Đường dẫn mà cookie có hiệu lực (ở đây là toàn bộ ứng dụng)
        response.addCookie(cookie);
    }

    // Xóa cookie dựa trên tên
    public void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0); // Đặt thời gian sống của cookie là 0 để xóa nó
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}