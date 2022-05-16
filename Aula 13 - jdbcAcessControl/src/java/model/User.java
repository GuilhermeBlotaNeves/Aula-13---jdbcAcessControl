/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static db.CreateListener.exception;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Fatec
 */
public class User {
    private String username;
    private long passwordHash;
    private String name;
    
    public static User getUser(String username, String password) throws Exception{
        User user = null;
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:mytasks.db";
        Connection con = DriverManager.getConnection(url);
        PreparedStatement stmt = con.prepareStatement(
            "select * from users where username = ? and pass_hash = ?"
        );
        stmt.setString(1, username);
        stmt.setLong(2, (username+password).hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            user = new User(
                    rs.getString("username"), 
                    rs.getLong("pass_hash"), 
                    rs.getString("name")
            );
        }
        rs.close(); stmt.close(); con.close();
        return user;
    }
    
    public User(String username, long passwordHash, String name) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash) {
        this.passwordHash = passwordHash;
    }
    
}
