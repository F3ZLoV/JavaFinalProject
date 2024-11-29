/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.io.*;
/**
 *
 * @author F3ZLoV
 */
public class DB_MAN {
    private String strDriver = "com.mysql.cj.jdbc.Driver"; // MySQL Driver
    private String strURL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC"; // MySQL URL
    private String strUser = "root"; // MySQL 유저 이름
    private String strPWD = "root"; // MySQL 비밀번호

    private Connection DB_con;
    private Statement DB_stmt;

    public void dbOpen() throws Exception {
        Class.forName(strDriver);
        DB_con = DriverManager.getConnection(strURL, strUser, strPWD);
        DB_stmt = DB_con.createStatement();
    }

    public void dbClose() throws Exception {
        if (DB_stmt != null) DB_stmt.close();
        if (DB_con != null) DB_con.close();
    }

    public boolean checkDuplicateId(String inputId) throws SQLException {
        String query = "SELECT COUNT(*) FROM member WHERE ID = ?";
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, inputId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0; // 중복된 아이디가 있으면 true 반환
        }
    }

    public void insertMember(String id, String password, String name) throws Exception {
        String query = "INSERT INTO member (ID, PASSWORD, NAME) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, password); // 비밀번호를 그대로 저장
            pstmt.setString(3, name);
            pstmt.executeUpdate();
        }
    }

    public boolean validateLogin(String id, String password) throws SQLException {
        String query = "SELECT PASSWORD FROM member WHERE ID = ?";
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("PASSWORD");
                return storedPassword.equals(password); // 입력한 비밀번호와 비교
            }
        }
        return false;
    }
}
