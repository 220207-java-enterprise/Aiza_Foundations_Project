package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.responses.AppUserResponse;
import com.revature.foundations.dtos.responses.Principal1;
import com.revature.foundations.dtos.responses.ResourceCreationResponse;
import com.revature.foundations.models.ErsReimbursements;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
// TODO login & token(already in AuthS/users), Authorize & reimbursement
public class ReimbServlet extends HttpServlet {


            private static Logger logger = LogManager.getLogger(UserServlet.class);

            private final ReimbService reimbService;
            private final ObjectMapper mapper;
            private final TokenService tokenService;

            public ReimbServlet(TokenService tokenService, UserService userService, ObjectMapper mapper) {
                this.userService = userService;
                this.tokenService = tokenService;
                this.mapper = mapper;
            }
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      List<AppUserResponse> users = reimbService.getAllUsers();//getting all users in dB//TODO change to getAuthorId in dao and service?
      String payload = mapper.writeValueAsString(users);//writing string
      resp.setContentType("application/json");
      resp.getWriter().write(payload);

      logger.debug("UserServlet#doGet returned successfully");
  }

  // TODO do I need to create a new header for posting?
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Principal1 requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
    if (requester == null) {
        logger.warn("Unauthenticated request made to UserServlet#doGet");
        resp.setStatus(401);
        return;
    }
    if (!requester.getRole().equals("ADMIN")) {//if you have authorization//TODO equals username?
        logger.warn("Unauthorized request made by user: " + requester.getUsername());
        resp.setStatus(403); // FORBIDDEN
        return;
    }

      PrintWriter respWriter = resp.getWriter();
            try{
                NewReimbRequest newReimbRequest = mapper.readValue(req.getInputStream(), NewReimbRequest.class);
                ErsReimbursements newReimb = reimbService.reimbInput(newReimbRequest);
                resp.setStatus(201);  // CREATED
                resp.setContentType("application/json");
                String payload = mapper.writeValueAsString(//new ResourceCreationResponse(newUser.getUserId()))//TODO resource creation?
//                        TODO Do I need an Id?
                String payload = mapper.writeValueAsString(new ResourceCreationResponse(newUser.getUserId()));//write into the database
                respWriter.write(payload);

            } catch (InvalidRequestException | DatabindException e) {
                 e.printStackTrace();
                 resp.setStatus(400); // BAD REQUEST
            } catch (
                ResourceConflictException e) {
                resp.setStatus(409); // CONFLICT
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                resp.setStatus(500);
    }
 }
}


