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
    
     public static ArrayList<User> getAllUsers() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:mytasks.db";
        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery
        ("select * from users order by name");
        while(rs.next()){
            list.add(new User(
                    rs.getString("username"), 
                    rs.getLong("pass_hash"), 
                    rs.getString("name")
            ));
        }
        rs.close(); stmt.close(); con.close();
        return list;
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
