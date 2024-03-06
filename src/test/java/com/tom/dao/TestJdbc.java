package com.tom.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/c2202l_javaee_ebook?useSSL=false";
        String user = "root";
        String pass = "trancckute11";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConnect = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
