package com.projects2024.flightreservation2.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SecurityService {
    boolean login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
