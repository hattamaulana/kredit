/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.github.hattamaulana.controller.CustomerController;
import com.github.hattamaulana.entities.Customer;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author ngub
 */
public class CustomerTest {
    private CustomerController controller;
    
    public CustomerTest() {
        controller = new CustomerController();
    }
    
    @Test
    public void testAddCustomer() {
        System.out.println("Test Add Customer : ");
        
        Customer customer = new Customer();
        customer.setName("Mahatta Maulana");
        customer.setRole(-1);
        customer.setAddress("Jl. Candi 3C No 4");
        customer.setPhoneNumber("+6289898989819");
        
        boolean expected = true;
        boolean actual   = controller.addCustomer(customer);
        
        Assert.assertEquals(expected, actual);
        
        System.out.println("* OK");
    }
}
