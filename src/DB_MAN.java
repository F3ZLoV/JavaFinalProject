/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
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
    
    public void createAccount(String userId, String accountNumber, String accountType) throws SQLException {
        String query = "INSERT INTO account (USER_ID, ACCOUNT_NUMBER, BALANCE, ACCOUNT_TYPE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, accountNumber);
            pstmt.setInt(3, 0); 
            pstmt.setString(4, accountType);
            pstmt.executeUpdate();
        }
    }
    
    private String generateRandomAccountNumber() {
        long min = 1000000000L; // 10자리 최소값
        long max = 9999999999L; // 10자리 최대값
        long randomNumber = min + (long) (Math.random() * (max - min + 1));
        return String.valueOf(randomNumber);
    }
    
    public String generateAccountNumber() throws SQLException {
        String accountNumber;
        String checkQuery = "SELECT COUNT(*) FROM account WHERE ACCOUNT_NUMBER = ?";

        do {
            accountNumber = generateRandomAccountNumber(); // 랜덤 계좌 번호 생성
            try (PreparedStatement pstmt = DB_con.prepareStatement(checkQuery)) {
                pstmt.setString(1, accountNumber);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    break; // 중복되지 않는 계좌 번호
                }
            }
        } while (true);

        return accountNumber;
    }
    
    
    public List<String[]> getAccounts(String userId) throws SQLException {
        String query = "SELECT ACCOUNT_NUMBER, BALANCE, ACCOUNT_TYPE FROM account WHERE USER_ID = ?";
        List<String[]> accounts = new ArrayList<>();
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String accountNumber = rs.getString("ACCOUNT_NUMBER");
                String balance = String.valueOf(rs.getInt("BALANCE"));
                String accountType = rs.getString("ACCOUNT_TYPE");
                accounts.add(new String[]{accountNumber, balance, accountType});
            }
        }
        return accounts;
    }

    public void deleteAccount(String accountNumber) throws SQLException {
        String query = "DELETE FROM account WHERE ACCOUNT_NUMBER = ?";
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);
            pstmt.executeUpdate();
        }
    }

    public void updateBalance(String accountNumber, long amount, String description) throws SQLException {
        String updateBalanceQuery = "UPDATE account SET BALANCE = BALANCE + ? WHERE ACCOUNT_NUMBER = ?";
        String insertTransactionQuery = "INSERT INTO transaction (ACCOUNT_NUMBER, AMOUNT, DESCRIPTION) VALUES (?, ?, ?)";

        try (PreparedStatement updateStmt = DB_con.prepareStatement(updateBalanceQuery);
             PreparedStatement insertStmt = DB_con.prepareStatement(insertTransactionQuery)) {
            // 잔액 update
            updateStmt.setLong(1, amount);
            updateStmt.setString(2, accountNumber);
            updateStmt.executeUpdate();

            // 거래 기록
            insertStmt.setString(1, accountNumber);
            insertStmt.setLong(2, amount);
            insertStmt.setString(3, description);
            insertStmt.executeUpdate();
        }
    }
    
    public void transferMoney(String senderAccountNumber, String receiverAccountNumber, long amount) throws SQLException {
        String updateSenderQuery = "UPDATE account SET BALANCE = BALANCE - ? WHERE ACCOUNT_NUMBER = ?";
        String updateReceiverQuery = "UPDATE account SET BALANCE = BALANCE + ? WHERE ACCOUNT_NUMBER = ?";
        String insertTransactionQuery = "INSERT INTO transaction (ACCOUNT_NUMBER, TARGET_ACCOUNT, AMOUNT, DESCRIPTION) VALUES (?, ?, ?, ?)";

        try (PreparedStatement updateSenderStmt = DB_con.prepareStatement(updateSenderQuery);
             PreparedStatement updateReceiverStmt = DB_con.prepareStatement(updateReceiverQuery);
             PreparedStatement insertTransactionStmt = DB_con.prepareStatement(insertTransactionQuery)) {

            // 송금 계좌 잔액 업데이트
            updateSenderStmt.setLong(1, amount);
            updateSenderStmt.setString(2, senderAccountNumber);
            updateSenderStmt.executeUpdate();

            // 수취 계좌 잔액 업데이트
            updateReceiverStmt.setLong(1, amount);
            updateReceiverStmt.setString(2, receiverAccountNumber);
            updateReceiverStmt.executeUpdate();

            // 거래 기록 (송금자)
            insertTransactionStmt.setString(1, senderAccountNumber);
            insertTransactionStmt.setString(2, receiverAccountNumber);
            insertTransactionStmt.setLong(3, -amount);
            insertTransactionStmt.setString(4, "송금");
            insertTransactionStmt.executeUpdate();

            // 거래 기록 (수취인)
            insertTransactionStmt.setString(1, receiverAccountNumber);
            insertTransactionStmt.setString(2, senderAccountNumber);
            insertTransactionStmt.setLong(3, amount);
            insertTransactionStmt.setString(4, "송금 수취");
            insertTransactionStmt.executeUpdate();
        }
    }
    
    public List<String[]> getTransactionHistory(String accountNumber) throws SQLException {
        String query = "SELECT AMOUNT, TARGET_ACCOUNT, DESCRIPTION, TRANSACTION_DATE FROM transaction WHERE ACCOUNT_NUMBER = ? ORDER BY TRANSACTION_DATE DESC";
        List<String[]> transactions = new ArrayList<>();
        try (PreparedStatement pstmt = DB_con.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String amount = String.valueOf(rs.getLong("AMOUNT"));
                String targetAccount = rs.getString("TARGET_ACCOUNT") != null ? rs.getString("TARGET_ACCOUNT") : "-";
                String description = rs.getString("DESCRIPTION");
                String date = rs.getString("TRANSACTION_DATE");
                transactions.add(new String[]{amount, targetAccount, description, date});
            }
        }
        return transactions;
    }
}
