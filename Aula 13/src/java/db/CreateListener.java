/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

/**
 * Web application lifecycle listener.
 *
 * @author rlarg
 */
public class CreateListener implements ServletContextListener {
    public static Exception exception = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:mytasks.db";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            //stmt.execute("drop table users");
            stmt.execute("create table IF NOT EXISTS users(\n" +
                        "    username varchar primary key, \n" +
                        "    pass_hash integer not null,\n" +
                        "    name varchar not null\n" +
                        ")");
            stmt.execute("insert OR IGNORE into users values"
                    + "('admin',20761617,'Administrador')");
            stmt.execute("insert OR IGNORE into users values"
                    + "('fulano',-1196589817,'Fulano da Silva')");
            stmt.close();
            con.close();
        }catch(Exception ex){
            exception = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}