package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    public static String reportLocation = System.getProperty("user.dir") + "/src/app/";
    private String driver, database, user, password;
    
    public Database (String driver, String url, String user, String pass) {
    this.driver     = driver;
    this.database   = url;
    this.user       = user;
    this.password   = pass;
  }

  public Connection connect () {
    try {
      Class.forName (this.driver);
      return DriverManager.getConnection (this.database, this.user, this.password);
    }
    catch (ClassNotFoundException error) {
      JOptionPane.showMessageDialog(null, "Maaf Anda belum membuka aplikasi XAMPP.");
    }
    catch (SQLException error) {
      JOptionPane.showMessageDialog(null, "Maaf Anda belum membuka aplikasi XAMPP.");
    }
    return null;
  }

  public void disconnect (Connection connect) {
    if (connect != null) {
      try {
        connect.close ();
      }
      catch (SQLException error) {
        System.out.println ("SQL Eroor disconnect !");
        error.printStackTrace ();
      }
    }
  }
}
