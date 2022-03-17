package com.revature.foundations.servlets;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.responses.AppUserResponse;
import com.revature.foundations.dtos.responses.Principal1;
import com.revature.foundations.dtos.responses.ResourceCreationResponse;
import com.revature.foundations.models.ErsUser;
import com.revature.foundations.services.TokenService;
import com.revature.foundations.services.UserService;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


// Mapping: /users/*
public class UserServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(UserServlet.class);

    private final UserService userService;
    private final ObjectMapper mapper;
    private final TokenService tokenService;

    public UserServlet(TokenService tokenService, UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get all users from DB if authorized
//        List<AppUserResponse> response = userService.getAllUsers();
//        resp.setContentType("application/json");
//        PrintWriter respWriter = resp.getWriter();
//        String responseJson = mapper.writeValueAsString(response);
//        respWriter.write(responseJson);
        String[] reqFrags = req.getRequestURI().split("/");//TODO what does this look like?
        if (reqFrags.length == 4 && reqFrags[3].equals("availability")) {//let's make a request
            checkAvailability(req, resp);//check if username and password are available, set response status
            return; // necessary, otherwise we end up doing more work than was requested
        }
//
//        // TODO implement some security logic here to protect sensitive operations
//
//        // get users (all, by id, by w/e)
//        HttpSession session = req.getSession(false);
//        if (session == null) {
//            resp.setStatus(401);
//            return;
//        }
//

        Principal1 requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));// creating header: requesting token of authorization

        if (requester == null) {
            logger.warn("Unauthenticated request made to UserServlet#doGet");
            resp.setStatus(401);
            return;
        }
        if (!requester.getRole().equals("ADMIN")) {//if you have authorization
            logger.warn("Unauthorized request made by user: " + requester.getUsername());
            resp.setStatus(403); // FORBIDDEN
            return;
        }

        List<AppUserResponse> users = userService.getAllUsers();//getting all users in dB
        String payload = mapper.writeValueAsString(users);//writing string
        resp.setContentType("application/json");
        resp.getWriter().write(payload);

        logger.debug("UserServlet#doGet returned successfully");

    }

    // registration endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//register new user

        PrintWriter respWriter = resp.getWriter();

        try {

            NewUserRequest newUserRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);//request new user
            ErsUser newUser = userService.register(newUserRequest);//register new user
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(newUser.getUserId()));//write into the database
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            e.printStackTrace();
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp.setStatus(500);
        }

    }

    protected void checkAvailability(HttpServletRequest req, HttpServletResponse resp) {
        String usernameValue = req.getParameter("username");
        String emailValue = req.getParameter("email");
        if (usernameValue != null) {
            if (userService.isUsernameAvailable(usernameValue)) {//if username is available in the system
                resp.setStatus(204); // NO CONTENT
            } else {
                resp.setStatus(409);
            }
        } else if (emailValue != null) {
            if (userService.isEmailAvailable(emailValue)) {
                resp.setStatus(204);
            } else {
                resp.setStatus(409);
            }
        }
    }

}
