/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import web.User;

/**
 *
 * @author usuário
 */
public class dblistener implements ServletContextListener {

    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:E:\\banco\\P2POO.db";
    
    public static String step = null;
    public static Exception exception = null;
    
    public static Connection getConnection() throws Exception {
         return DriverManager.getConnection(URL);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        step = "Criando tabelas";
        try {
            step = "Conectando com o banco";
            Class.forName(CLASS_NAME);
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            step = "Criando tabela de usuário";
            String sql = "CREATE TABLE IF NOT EXISTS users("
                    + "name VARCHAR(200) NOT NULL,"
                    + "login VARCHAR(50) UNIQUE NOT NULL,"
                    + "password_hash varchar(200) NOT NULL,"
                    + "role VARCHAR(20) NOT NULL"
                    + ")";
            stmt.execute(sql);
            if(User.getUsers().isEmpty()){
                step = "Inserindo usuário administrativo";
                sql = "INSERT INTO users(name, login, password_hash, role) "
                    + "VALUES('Administrador', 'admin', '1234', 'ADMIN')";
                stmt.execute(sql);
                sql = "INSERT INTO users(name, login, password_hash, role) "
                    + "VALUES('Danilo', 'danilo@danilo', '1234', 'USER')";            
                stmt.execute(sql);
            }
                    
          
            stmt.close();
            con.close();            
        } catch(Exception ex) {
            exception = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}