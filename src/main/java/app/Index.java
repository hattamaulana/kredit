/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;

import app.data.Database;
import app.data.database.ConnectionDatabase;
import app.data.database.Error;
import java.sql.Connection;
/**
 *
 * @author Hatta Maulana
 */
public class Index {
    
    public static void main(String[] args){
        ConnectionDatabase db = new Database().getConnection();
        
        try {
            db.disconnect(checkConnection(db));
        } catch (NullPointerException err) { err.printStackTrace(); }
        
    }
    
    private static Connection checkConnection (ConnectionDatabase database){
        return database.connect(new Error(){
            @Override
            public boolean errorMessage(boolean warning) {

                JOptionPane.showMessageDialog(null, "Please Contact to your Server Administrator to setup database, Thank's.");

                if (warning) {
                    int exit = JOptionPane.showConfirmDialog(null, "Apakah Anda ingin menghentikan proses aplikasi ini ? ");
                    if (exit == 0) {
                        System.exit(0);
                    }
                }

                return true;
            }
        });
    }
}
