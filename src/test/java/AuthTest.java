/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.github.hattamaulana.controller.AuthController;
import com.github.hattamaulana.entities.Employee;
import com.github.hattamaulana.lib.IUser;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author ngub
 */
public class AuthTest {
    private AuthController auth;
    
    public AuthTest() {
        auth = new AuthController();
    }
    
    @Test
    public void testUserLogin() {
        System.out.println("Tes Login User : ");
        
        IUser expected = new IUser() {
            @Override
            public void redirectToView() {
                
            }
        };
        IUser actual   = auth.login("karyawan", "karyawan");
        
        Assert.assertEquals(expected, actual);
        
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
