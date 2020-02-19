/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author user
 */
public class DBConnections {

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/poll");
            conn = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DBConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
