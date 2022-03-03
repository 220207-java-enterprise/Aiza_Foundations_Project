//package com.revature.foundations.daos;
//
//import com.revature.foundations.models.ErsUserRoles;
//import com.revature.foundations.models.UserRole;
//import com.revature.foundations.util.ConnectionFactory;
//import com.revature.foundations.util.exceptions.DataSourceException;
//import com.revature.foundations.util.exceptions.ResourcePersistenceException;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ErsUserRolesDAO implements CrudDAO<ErsUserRoles> {{
//
//
//        private final String rootSelect = "SELECT " +
//                "eu.userId, eu.username, eu.email, eu.email, eu.password, eu.givenName, eu.surname, ur.role " +
//                "FROM ers_users eu " +
//                "JOIN ers_user_roles ur " +
//                "ON eu.role = ur.id ";
//
//        public ErsUserRoles findUserByUsername(String username) {
//
//        ErsUserRoles user = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
//                pstmt.setString(1, username);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    user = new ErsUserRoles();
//                    user.setRoleId(rs.getString("roleId"));
//                    user.setRole(rs.getString("role"));
//
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            return user;
//        }
//
//        public ErsUserRoles findUserByEmail(String email) {
//
//        ErsUserRoles user = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
//                pstmt.setString(1, email);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    user = new ErsUserRoles();
//                    user.setRoleId(rs.getString("roleId"));
//                    user.setRole(rs.getString("role"));
//
//                }
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//
//            return user;
//
//        }
//
//        public ErsUserRoles findUserByUsernameAndPassword(String username, String password) {
//
//        ErsUserRoles authUser = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
//                pstmt.setString(1, username);
//                pstmt.setString(2, password);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    authUser = new ErsUserRoles();
//                    authUser.setRoleId(rs.getString("roleId"));
//                    authUser.setRole(rs.getString("role"));
//
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//
//            return authUser;
//        }
//
//        @Override
//        public void save(ErsUserRoles newUser) {
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                conn.setAutoCommit(false);
//                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO app_users VALUES (?, ?, ?, ?, ?, ?, ?)");
//                pstmt.setString(1, newUser.getRoleId());
//                pstmt.setString(2, newUser.getRole());
//
//
//                int rowsInserted = pstmt.executeUpdate();
//                if (rowsInserted != 1) {
//                    conn.rollback();
//                    throw new ResourcePersistenceException("Failed to persist user to data source");
//                }
//
//                conn.commit();
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//        }
//
//        @Override
//        public ErsUserRoles getById(String id) {
//
//        ErsUserRoles user = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE id = ?");
//                pstmt.setString(1, id);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    user = new ErsUserRoles();
//                    user.setRoleId(rs.getString("roleId"));
//                    user.setRole(rs.getString("role"));
//
//                }
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//
//            return user;
//
//        }
//
//        @Override
//        public List<ErsUserRoles> getAll() {
//
//            List<ErsUserRoles> users = new ArrayList<>();
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                ResultSet rs = conn.createStatement().executeQuery(rootSelect);
//                while (rs.next()) {
//                    ErsUserRoles user = new ErsUserRoles();
//                    user.setRoleId(rs.getString("roleId"));
//                    user.setRole(rs.getString("role"));
//
//                }
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//
//            return users;
//        }
//
//        @Override
//        public void update(ErsUserRoles updatedUser) {
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                conn.setAutoCommit(false);
//                PreparedStatement pstmt = conn.prepareStatement("UPDATE app_users " +
//                        "SET first_name = ?, " +
//                        "last_name = ?, " +
//                        "email = ?, " +
//                        "username = ?, " +
//                        "password = ? " +
//                        "WHERE id = ?");
//                pstmt.setString(1, updatedUser.getRoleId());
//                pstmt.setString(2, updatedUser.getRole());
//
//
//                // TODO allow role to be updated as well
//
//                int rowsInserted = pstmt.executeUpdate();
//                if (rowsInserted != 1) {
//                    throw new ResourcePersistenceException("Failed to update user data within datasource.");
//                }
//
//                conn.commit();
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//        }
//
//        @Override
//        public void deleteById(String id) {
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                conn.setAutoCommit(false);
//                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ers_users WHERE id = ?");
//                pstmt.setString(1, id);
//
//                int rowsInserted = pstmt.executeUpdate();
//                if (rowsInserted != 1) {
//                    conn.rollback();
//                    throw new ResourcePersistenceException("Failed to delete user from data source");
//                }
//
//                conn.commit();
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//        }
//    }
//
//
