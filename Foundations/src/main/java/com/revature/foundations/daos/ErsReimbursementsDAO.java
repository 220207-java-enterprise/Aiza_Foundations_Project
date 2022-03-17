package com.revature.foundations.daos;
//
import com.revature.foundations.models.ErsReimbursements;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ErsReimbursementsDAO implements CrudDAO<ErsReimbursements> {


        private final String rootSelect = "SELECT " +
                "er.Reimb_id, er.Amount, er.Submitted, er.Resolved, er.Description," +
                "er. Receipt, er.Payment_id, er.Author_id, er.Resolver_id " +
                "er.Status_id, er.Type_id " +
                "FROM ers_reimbursements er " ;

        public ErsReimbursements findUserByUsername(String username) {

            ErsReimbursements user = null;

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
                pstmt.setString(1, username);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new ErsReimbursements();
                    user.setReimbId(rs.getString("reimId"));
                    user.setAmount(rs.getInt("amount"));
                    user.setSubmitted(rs.getTimestamp("submitted"));
                    user.setResolved(rs.getTimestamp("resolved"));
                    user.setDescription(rs.getString("description"));
                   // user.setReceipt(rs.getString("receipt"));
                    user.setPaymentId(rs.getString("paymentId"));
                    user.setAuthorId(rs.getString("authorId"));
                    user.setResolverId(rs.getString("resolverId"));
                    user.setStatusId(rs.getString("statusId"));
                    user.setTypeId(rs.getString("typeId"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return user;
        }

        public ErsReimbursements findUserByEmail(String email) {

            ErsReimbursements user = null;

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
                pstmt.setString(1, email);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new ErsReimbursements();
                    user.setReimbId(rs.getString("reimId"));
                    user.setAmount(rs.getInt("amount"));
                    user.setSubmitted(rs.getTimestamp("submitted"));
                    user.setResolved(rs.getTimestamp("resolved"));
                    user.setDescription(rs.getString("description"));
                   // user.setReceipt(rs.getString("receipt"));
                    user.setPaymentId(rs.getString("paymentId"));
                    user.setAuthorId(rs.getString("authorId"));
                    user.setResolverId(rs.getString("resolverId"));
                    user.setStatusId(rs.getString("statusId"));
                    user.setTypeId(rs.getString("typeId"));
                }

            } catch (SQLException e) {
                throw new DataSourceException(e);
            }

            return user;

        }

        public ErsReimbursements findUserByUsernameAndPassword(String username, String password) {

            ErsReimbursements authUser = null;

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    authUser = new ErsReimbursements();
                    authUser.setReimbId(rs.getString("ReimId"));
                    authUser.setAmount(rs.getInt("amount"));
                    authUser.setSubmitted(rs.getTimestamp("submitted"));
                    authUser.setResolved(rs.getTimestamp("resolved"));
                    authUser.setDescription(rs.getString("description"));
                   // authUser.setReceipt(rs.getString("receipt"));
                    authUser.setPaymentId(rs.getString("paymentId"));
                    authUser.setAuthorId(rs.getString("authorId"));
                    authUser.setResolverId(rs.getString("resolverId"));
                    authUser.setStatusId(rs.getString("statusId"));
                    authUser.setTypeId(rs.getString("typeId"));

                }

            } catch (SQLException e) {
                throw new DataSourceException(e);
            }

            return authUser;
        }

        @Override
        public void save(ErsReimbursements newUser) {

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                conn.setAutoCommit(false);
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO app_users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, newUser.getReimbId());
                pstmt.setDouble(2, newUser.getAmount());
                pstmt.setTimestamp(3, newUser.getSubmitted());
                pstmt.setTimestamp(4, newUser.getResolved());
                pstmt.setString(5, newUser.getDescription());
               // pstmt.setString(6, newUser.getReceipt());
                pstmt.setString(7, newUser.getPaymentId());
                pstmt.setString(8, newUser.getAuthorId());
                pstmt.setString(9, newUser.getResolverId());
                pstmt.setString(10, newUser.getStatusId());
                pstmt.setString(11, newUser.getTypeId());



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
        public ErsReimbursements getById(String id) {

            ErsReimbursements user = null;

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE id = ?");
                pstmt.setString(1, id);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new ErsReimbursements();
                    user.setReimbId(rs.getString("Reimb_id"));
                    user.setAmount(rs.getInt("Amount"));
                    user.setSubmitted(rs.getTimestamp("Submitted"));
                    user.setResolved(rs.getTimestamp("Resolved"));
                    user.setDescription(rs.getString("Description"));
                   // user.setReceipt(rs.getString("receipt"));
                    user.setPaymentId(rs.getString("Payment_id"));
                    user.setAuthorId(rs.getString("Author_id"));
                    user.setResolverId(rs.getString("Resolver_id"));
                    user.setStatusId(rs.getString("Status_id"));
                    user.setTypeId(rs.getString("Type_id"));

                }

            } catch (SQLException e) {
                throw new DataSourceException(e);
            }

            return user;

        }

        @Override
        public List<ErsReimbursements> getAll() {

            List<ErsReimbursements> users = new ArrayList<>();

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                ResultSet rs = conn.createStatement().executeQuery(rootSelect);
                while (rs.next()) {
                    ErsReimbursements user = new ErsReimbursements();
                    user.setReimbId(rs.getString("Reimb_id"));
                    user.setAmount(rs.getInt("Amount"));
                    user.setSubmitted(rs.getTimestamp("Submitted"));
                    user.setResolved(rs.getTimestamp("Resolved"));
                    user.setDescription(rs.getString("Description"));
                   // user.setReceipt(rs.getString("receipt"));
                    user.setPaymentId(rs.getString("Payment_id"));
                    user.setAuthorId(rs.getString("Author_id"));
                    user.setResolverId(rs.getString("Resolver_id"));
                    user.setStatusId(rs.getString("Status_id"));
                    user.setTypeId(rs.getString("Type_id"));

                }
            } catch (SQLException e) {
                throw new DataSourceException(e);
            }

            return users;
        }

        @Override
        public void update(ErsReimbursements updatedUser) {
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

                conn.setAutoCommit(false);
                PreparedStatement pstmt = conn.prepareStatement("UPDATE ers_reimbursements " +
                        "SET Reimb_id = ?, " +
                        "Amount = ?, " +
                        "Submitted = ?, " +
                        "Resolved = ?, " +
                        "Description = ? " +
                        "Payment_id = ? " +
                        "Author_id = ? " +
                        "Resolver_id = ? " +
                        "Status_id = ? " +
                        "Type_id = ? " +
                        "WHERE Reimb_id = ?");
                pstmt.setString(1, updatedUser.getReimbId());
                pstmt.setDouble(2, updatedUser.getAmount());
                pstmt.setTimestamp(3, updatedUser.getSubmitted());
                pstmt.setTimestamp(4, updatedUser.getResolved());
                pstmt.setString(5, updatedUser.getDescription());
               // pstmt.setString(6, updatedUser.getReceipt());
                pstmt.setString(7, updatedUser.getPaymentId());
                pstmt.setString(8, updatedUser.getAuthorId());
                pstmt.setString(9, updatedUser.getResolverId());
                pstmt.setString(10, updatedUser.getStatusId());
                pstmt.setString(11, updatedUser.getTypeId());

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
