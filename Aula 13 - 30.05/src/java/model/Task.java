/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author rlarg
 */
public class Task {
    private int id;
    private String username;
    private String title;
    
    public static ArrayList<Task> getAllTasks() throws Exception{
        ArrayList<Task> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:mytasks.db";
        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery
        ("select * from tasks order by id");
        while(rs.next()){
            list.add(new Task(
                    rs.getInt("id"), 
                    rs.getString("username"),
                    rs.getString("title")
            ));
        }
        rs.close(); stmt.close(); con.close();
        return list;
    }

    public static ArrayList<Task> getUserTasks(String username) throws Exception{
        ArrayList<Task> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:mytasks.db";
        Connection con = DriverManager.getConnection(url);
        String sql = "select * from tasks "
                + "where username=? order by id";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            list.add(new Task(
                    rs.getInt("id"), 
                    rs.getString("username"),
                    rs.getString("title")
            ));
        }
        rs.close(); stmt.close(); con.close();
        return list;
    }
    
    public static void addTask(Task task) throws Exception{
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:mytasks.db";
        Connection con = DriverManager.getConnection(url);
        PreparedStatement stmt = con.prepareStatement(""
                + "INSERT INTO tasks(username, title)"
                + "values(?,?)");
        stmt.setString(1, task.getUsername());
        stmt.setString(2, task.getTitle());
        stmt.execute();
        stmt.close(); con.close();
    }
    
    public static void deleteTask(int id) throws Exception{
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:mytasks.db";
        Connection con = DriverManager.getConnection(url);
        PreparedStatement stmt = con.prepareStatement
        ("DELETE FROM tasks WHERE id=?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close(); con.close();
    }
    
    public Task(int id, String username, String title) {
        this.id = id;
        this.username = username;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}