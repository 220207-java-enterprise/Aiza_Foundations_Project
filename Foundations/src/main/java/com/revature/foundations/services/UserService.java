package com.revature.foundations.services;

import com.revature.foundations.dtos.requests.LoginRequest;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.responses.AppUserResponse;
import com.revature.foundations.models.ErsUser;
import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.models.ErsUserRoles;
import com.revature.foundations.models.UserRole;
import com.revature.foundations.util.exceptions.AuthenticationException;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {

    private UserDAO userDAO; // a dependency of UserService

    // Constructor injection
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<AppUserResponse> getAllUsers() {//Let's get all the user info in the database

        // Pre-Java 8 mapping logic (without Streams)
//        List<AppUser> users = userDAO.getAll();
//        List<AppUserResponse> userResponses = new ArrayList<>();
//        for (AppUser user : users) {
//            userResponses.add(new AppUserResponse(user));
//        }
//        return userResponses;

        // Java 8+ mapping logic (with Streams)
        return userDAO.getAll()
                .stream()
                .map(AppUserResponse::new) // intermediate operation
                .collect(Collectors.toList()); // terminal operation
    }

    public ErsUser register(NewUserRequest newUserRequest) {//let's check the credentials

        ErsUser newUser = newUserRequest.extractUser(); //get values for newUser in NewUserRequest (Get)

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Bad registration details given.");//not (valid credentials)
        }

        boolean usernameAvailable = isUsernameAvailable(newUser.getUsername());//true or false is there a (username that already exist?)
        boolean emailAvailable = isEmailAvailable(newUser.getEmail());//true or false is there a (password that already exist?)

        if (!usernameAvailable || !emailAvailable) {// OR (Do the credentials exist? Yes they do? Let's send a message if they do exist and are not available to be used)
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new ResourceConflictException(msg);
        }

        // TODO encrypt provided password before storing in the database

        newUser.setUserId(UUID.randomUUID().toString());// let's create a random ID for the NEW User
        newUser.setRole(new ErsUserRoles("7c3521f5-ff75-4e8a-9913-01d15ee4dc97", "BASIC_USER")); // All newly registered users start as BASIC_USER
        userDAO.save(newUser);//now let's (save) them in the system

        return newUser;
    }

    public ErsUser login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();//let's get the username
        String password = loginRequest.getPassword();//let's get the password

        if (!isUsernameValid(username) || !isPasswordValid(password)) {//if wrong username or password (invalid credentials!)
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        // TODO encrypt provided password (assumes password encryption is in place) to see if it matches what is in the DB

        ErsUser authUser = userDAO.findUserByUsernameAndPassword(username, password);//let's find the already existing user/pass

        if (authUser == null) {//no user/pass
            throw new AuthenticationException();
        }

        return authUser;

    }

    public boolean isUserValid(ErsUser appUser) {

        // First name and last name are not just empty strings or filled with whitespace
        if (appUser.getGivenName().trim().equals("") || appUser.getSurname().trim().equals("")) {//are these empty in the database? check other values to make sure
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (!isUsernameValid(appUser.getUsername())) {
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (!isPasswordValid(appUser.getPassword())) {
            return false;
        }

        // Basic email validation
        return isEmailValid(appUser.getEmail());

    }

    public boolean isEmailValid(String email) {
        if (email == null) return false;
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }

    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    public boolean isUsernameAvailable(String username) {
        if (username == null || !isUsernameValid(username)) return false;
        return userDAO.findUserByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        if (email == null || !isEmailValid(email)) return false;
        return userDAO.findUserByEmail(email) == null;
    }

}