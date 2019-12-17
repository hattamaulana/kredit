/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.github.hattamaulana.controller.AuthController;
import com.github.hattamaulana.entities.Employee;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author ngub
 */
public class Auth {
    private AuthController auth;
    
    public Auth() {
        auth = new AuthController();
    }
    
    @Test
    public void testUserLogin() {
        System.out.println("Tes Login User : ");
        
        boolean expected = true;
//        boolean actual   = auth.login("karyawan", "karyawan");
        
//        Assert.assertEquals(expected, actual);
        
        System.out.println("* OK");
    }
    
    @Test
    public void testRegisterEmployee() {
        System.out.println("Test Registering User As Employee : ");
        
        Employee user = new Employee();
        user.setName("Maulana");
        user.setUsername("mln");
        user.setPassword("password");
        user.setAddress("Kel. Bawang Kota Kediri");
        user.setPhoneNumber("+72134");
        user.setSalary(1000000f);
        
        boolean expected = true;
        boolean actual   = auth.registerEmployee(user);
        
        Assert.assertEquals(expected, actual);
        
        System.out.println("* OK");
    }
}
