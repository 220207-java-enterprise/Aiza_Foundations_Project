package com.revature.foundations.daos;

import com.revature.foundations.models.ErsUser;
import com.revature.foundations.models.ErsUserRoles;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO attempt to centralize exception handling in service layer
public class UserDAO implements CrudDAO<ErsUser> {

    private final String rootSelect = "SELECT " +
            "eu.User_id, eu.Username, eu.Email, eu.Password, eu.Given_name, eu.Surname, eu.Is_active, eu.Role_id, eur.role " +
            "FROM ers_users eu " +
            "JOIN ers_user_roles eur " +
            "ON eu.Role_id = eur.role_id "
            ;

    public ErsUser findUserByUsername(String username) {

        ErsUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ErsUser();
                user.setUserId(rs.getString("User_id"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setActive(rs.getBoolean("Is_active"));
                user.setRole(new ErsUserRoles(rs.getString("Role_id"), rs.getString("role")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public ErsUser findUserByEmail(String email) {

        ErsUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ErsUser();
                user.setUserId(rs.getString("User_id"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setActive(rs.getBoolean("Is_active"));
                user.setRole(new ErsUserRoles(rs.getString("Role_id"), rs.getString("role")));

            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    public ErsUser findUserByUsernameAndPassword(String username, String password) {

        ErsUser authUser = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                authUser = new ErsUser();
                authUser.setUserId(rs.getString("User_id"));
                authUser.setUsername(rs.getString("Username"));
                authUser.setEmail(rs.getString("Email"));
                authUser.setPassword(rs.getString("Password"));
                authUser.setGivenName(rs.getString("Given_name"));
                authUser.setSurname(rs.getString("Surname"));
                authUser.setActive(rs.getBoolean("Is_active"));
                authUser.setRole(new ErsUserRoles(rs.getString("Role_id"), rs.getString("role")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);

        }

        return authUser;
    }

    @Override
    public void save(ErsUser newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
          PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_users VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUsername());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.setString(5, newUser.getGivenName());
            pstmt.setString(6, newUser.getSurname());
            pstmt.setBoolean(7, newUser.getIsActive());
            pstmt.setString(8, newUser.getRole().getRole());


            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public ErsUser getById(String id) {

        ErsUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ErsUser();
                user.setUserId(rs.getString("User_id"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setActive(rs.getBoolean("Is_active"));
                user.setRole(new ErsUserRoles(rs.getString("Role_id"), rs.getString("role")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    @Override
    public List<ErsUser> getAll() {

        List<ErsUser> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            System.out.println(rs);
            while (rs.next()) {
                ErsUser user = new ErsUser();
                user.setUserId(rs.getString("User_id"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setActive(rs.getBoolean("Is_active"));
                user.setRole(new ErsUserRoles(rs.getString("Role_Id"), rs.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;
    }


    @Override
    public void update(ErsUser updatedUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ers_users " +
                    "SET User_id = ?, " +
                    "Username = ?, " +
                    "Email = ?, " +
                    "Password = ?, " +
                    "GivenName = ? " +
                    "Surname = ? " +
                    "IsActive = ? " +
                    "Role = ? " +
                    "WHERE User_id = ?");
            pstmt.setString(1, updatedUser.getUserId());
            pstmt.setString(2, updatedUser.getUsername());
            pstmt.setString(3, updatedUser.getEmail());
            pstmt.setString(4, updatedUser.getPassword());
            pstmt.setString(5, updatedUser.getGivenName());
            pstmt.setString(6, updatedUser.getSurname());
            pstmt.setBoolean(7, updatedUser.getIsActive());
            pstmt.setString(8, updatedUser.getRole());

            // TODO allow role to be updated as well

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                throw new ResourcePersistenceException("Failed to update user data within datasource.");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ers_users WHERE id = ?");
            pstmt.setString(1, id);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to delete user from data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }
}
