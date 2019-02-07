/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data.database;

import app.data.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ngub
 */
public class ConnectionDatabase {
    private String driver, uri, user, pass;
    
    public ConnectionDatabase (String host, int port, String user, String pass, String database){
        this.driver = "org.mariadb.jdbc.Driver";
        this.user = user;
        this.pass = pass;
        // jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword
        this.uri  = "jdbc:mariadb://"+ host +":"+ port +"/"+ database +"?user="+ user +"&password="+ pass;
    }
    
    public Connection connect (Error errorMessage){
        boolean loop   = false;
        int peringatan = 0;
        
        do {
            
            try {
              Class.forName (this.driver);
              return DriverManager.getConnection (this.uri);
            } catch (ClassNotFoundException error) {
              error.printStackTrace();
              JOptionPane.showMessageDialog(null, "Please call your Developer :)");
            } catch (SQLException error) {
              loop = errorMessage.errorMessage((peringatan > 3) ? true : false);
            }
            
        } while (loop);
        
        return null;
    }
    
    public void disconnect (Connection connect) {
        if (connect != null) {
          try { connect.close (); }
          catch (SQLException error) { }
        }
    }
}
