/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import app.data.database.Error;
import app.data.database.ConnectionDatabase;

/**
 *
 * @author ngub
 */
public class Database {
    private static ConnectionDatabase connection;
    
    public Database (){
        Database.connection = new ConnectionDatabase(
                "localhost", 3306, 
                "root", "nganu", 
                "kredit"
        );
    }
    
    public ConnectionDatabase getConnection (){
        return Database.connection;
    }
}
