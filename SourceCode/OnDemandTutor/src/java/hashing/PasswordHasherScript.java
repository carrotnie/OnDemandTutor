/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class PasswordHasherScript {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement selectPstmt = null;
        PreparedStatement updatePstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                selectPstmt = conn.prepareStatement("SELECT Id, Password FROM Account");
                rs = selectPstmt.executeQuery();
                updatePstmt = conn.prepareStatement("UPDATE Account SET Password = ? WHERE Id = ?");
                
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String plainPassword = rs.getString("Password");
                    String hashedPassword = Hasher.hash(plainPassword);
                    
                    updatePstmt.setString(1, hashedPassword);
                    updatePstmt.setInt(2, id);
                    updatePstmt.executeUpdate();
                }
                System.out.println("Passwords have been hashed and updated successfully.");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (selectPstmt != null) {
                    selectPstmt.close();
                }
                if (updatePstmt != null) {
                    updatePstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
