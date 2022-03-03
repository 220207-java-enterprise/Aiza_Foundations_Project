//package com.revature.foundations.daos;
//
//import com.revature.foundations.models.ErsReimbursementTypes;
//import com.revature.foundations.util.ConnectionFactory;
//import com.revature.foundations.util.exceptions.DataSourceException;
//import com.revature.foundations.util.exceptions.ResourcePersistenceException;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ErsReimbursementTypesDAO implements CrudDAO<ErsReimbursementTypes> {
//
//
//        private final String rootSelect = "SELECT " +
//                "eu.userId, eu.username, eu.email, eu.email, eu.password, eu.givenName, eu.surname, ur.role " +
//                "FROM ers_users eu " +
//                "JOIN ers_user_roles ur " +
//                "ON eu.role = ur.id ";
//
//        public ErsReimbursementTypes findUserByUsername(String username) {
//
//            ErsReimbursementTypesDAO user = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
//                pstmt.setString(1, username);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    user = new ErsReimbursementTypesDAO();
//                    user.setTypeId(rs.getString("TypeId"));
//                    user.setType(rs.getString("Type"));
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
//        public ErsReimbursementTypes findUserByEmail(String email) {
//
//            ErsReimbursementTypes user = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
//                pstmt.setString(1, email);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    user = new ErsReimbursementTypes();
//                    user.setTypeId(rs.getString("TypeId"));
//                    user.setType(rs.getString("Type"));
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
//        public ErsReimbursementTypes findUserByUsernameAndPassword(String username, String password) {
//
//            ErsReimbursementTypes authUser = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
//                pstmt.setString(1, username);
//                pstmt.setString(2, password);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    authUser = new ErsReimbursementTypesDAO();
//                    authUser.setTypeId(rs.getString("TypeId"));
//                    authUser.setType(rs.getString("Type"));
//
//            } catch (SQLException e) {
//                throw new DataSourceException(e);
//            }
//
//            return authUser;
//        }
//
//        @Override
//        public void save(ErsReimbursementTypes newUser) {
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                conn.setAutoCommit(false);
//                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO app_users VALUES (?, ?, ?, ?, ?, ?, ?)");
//                pstmt.setString(1, newUser.getTypeId());
//                pstmt.setString(2, newUser.getType());
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
//        public ErsReimbursementTypes getById(String id) {
//
//            ErsReimbursementTypes user = null;
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE id = ?");
//                pstmt.setString(1, id);
//
//                ResultSet rs = pstmt.executeQuery();
//                if (rs.next()) {
//                    user = new ErsReimbursementTypes();
//                    user.setTypeId(rs.getString("TypeId"));
//                    user.setType(rs.getString("Type"));
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
//        public List<ErsReimbursementTypes> getAll() {
//
//            List<ErsReimbursementTypes> users = new ArrayList<>();
//
//            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//                ResultSet rs = conn.createStatement().executeQuery(rootSelect);
//                while (rs.next()) {
//                    ErsReimbursementTypes user = new ErsReimbursementTypes();
//                    user.setTypeId(rs.getString("TypeId"));
//                    user.setType(rs.getString("Type"));
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
//        public void update(ErsReimbursementTypes updatedUser) {
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
//                pstmt.setString(1, updatedUser.getTypeId());
//                pstmt.setString(2, updatedUser.getType());
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
//}
