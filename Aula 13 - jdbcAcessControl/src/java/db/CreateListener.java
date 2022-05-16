/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

/**
 * Web application lifecycle listener.
 *
 * @author Fatec
 */
public class CreateListener implements ServletContextListener {

    public static Exception exception = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:Mytasks.db";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            stmt.close();
            stmt.execute("create table IF NOT EXISTS users ( \n"
                    + " username varchar primary key ,    \n"
                    + " pass_hash integer not null,       \n"
                    + " name varchar not null              )");
            stmt.execute("insert OR IGNORE into users values "
                    + "('admin',20761617,'Administrador')");
            
            stmt.execute("insert OR IGNORE into users values "
                    + "('fulano',-1196589617,'Fulano Da Silva')");
            con.close();
        } catch (Exception ex) {
            exception = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
