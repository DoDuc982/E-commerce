package com.example.ecommerce.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    // Lưu dữ liệu vào session
    public void setAttribute(HttpServletRequest request, String attributeName, Object attributeValue) {
        HttpSession session = request.getSession();
        session.setAttribute(attributeName, attributeValue);
    }

    // Lấy dữ liệu từ session
    public Object getAttribute(HttpServletRequest request, String attributeName) {
        HttpSession session = request.getSession();
        return session.getAttribute(attributeName);
    }

    // Xóa dữ liệu từ session
    public void removeAttribute(HttpServletRequest request, String attributeName) {
        HttpSession session = request.getSession();
        session.removeAttribute(attributeName);
    }
}
