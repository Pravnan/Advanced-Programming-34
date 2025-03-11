package com.example.vehiclereservationapp.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI();
        String action = httpRequest.getParameter("action");

        System.out.println("[AuthFilter] Request received for: " + path + (action != null ? "?action=" + action : ""));

        // Allow access to login pages and static resources
        boolean isPublicPage = path.endsWith("login.jsp") ||
                path.endsWith("/login") ||
                path.contains("/static/") ||
                (path.endsWith("/customer") && "add".equals(action)); // Allow customer registration

        boolean isLoggedIn = (session != null) &&
                (session.getAttribute("loggedInAdmin") != null ||
                        session.getAttribute("loggedInDriver") != null ||
                        session.getAttribute("loggedInCustomer") != null);

        if (!isLoggedIn && !isPublicPage) {
            System.out.println("[AuthFilter] User not authenticated. Redirecting to login.");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        if (session != null) {
            boolean isAdmin = session.getAttribute("loggedInAdmin") != null;
            boolean isDriver = session.getAttribute("loggedInDriver") != null;
            boolean isCustomer = session.getAttribute("loggedInCustomer") != null;

            System.out.println("[AuthFilter] User Role - Admin: " + isAdmin + ", Driver: " + isDriver + ", Customer: " + isCustomer);

            // 🚨 **Block Customers from Listing Other Customers**
            if (isCustomer && path.contains("/customer") && "list".equals(action)) {
                System.out.println("[AuthFilter] Access denied: Customers cannot list other customers.");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/customer/dashboard");
                return;
            }

            // 🚨 **Restrict Drivers from Customer CRUD (except profile)**
            if (isDriver && path.contains("/customer") && !"edit".equals(action)) {
                System.out.println("[AuthFilter] Driver tried to access customer section: " + path);
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/driver/dashboard");
                return;
            }

            // 🚨 **Restrict Customers from Driver CRUD**
            if (isCustomer && path.contains("/driver") && !"edit".equals(action)) {
                System.out.println("[AuthFilter] Customer tried to access driver section: " + path);
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/customer/dashboard");
                return;
            }
        }

        System.out.println("[AuthFilter] Request allowed: " + path);
        chain.doFilter(request, response);
    }
}
