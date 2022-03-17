package com.revature.foundations.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.services.UserService;
import com.revature.foundations.servlets.AuthServlet;
import com.revature.foundations.servlets.UserServlet;
import com.revature.foundations.services.TokenService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing ERS application");

        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserServlet userServlet = new UserServlet(tokenService, userService, mapper);
        AuthServlet authServlet = new AuthServlet(tokenService, userService, mapper);

       // ErsReimbursementDAO reimbDAO = new ErsReimbursementDAO();
        //TODO do I need a reimbursement service?


        // Programmatic Servlet Registration
        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
       // context.addServlet("UserServlet", userServlet).addMapping("/users");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        //context.addServlet("ReimbServlet", reimbServlet).addMapping("/reimbursements");//get all reimbursements? make reimbursements?
        //TODO add endpoint to users or own endpoint?
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down Foundations web application");
    }

}
