package com.godigit.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {
    private static final String URL = "jdbc:postgresql://localhost:5432/insurance_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Chem@blue1";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
            String query = "SELECT * FROM customers";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");

                System.out.println(id + "\n " + first_name + "\n " + last_name);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
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
