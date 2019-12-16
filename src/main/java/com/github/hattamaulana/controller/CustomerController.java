/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.controller;

import com.github.hattamaulana.dao.CustomerDao;
import com.github.hattamaulana.entities.Customer;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ngub
 */
public class CustomerController {
    CustomerDao dao = new CustomerDao();
    
    public List<Customer> searchById(String id) {
        HashMap<String, Object> conditions = new HashMap();
        conditions.put("role", -1);
        conditions.put("id", id);
        
        return dao.search(conditions);
    }
    
    public List<Customer> searchCustomer(
            String name, String address, String phoneNumber
    ) {
        HashMap<String, Object> conditions = new HashMap();
        
        if (! "".equals(name))
            conditions.put("name", "%"+ name +"%");
        
        if (! "".equals(address))
            conditions.put("address", "%"+ address +"%");
        
        if (! "".equals(phoneNumber))
            conditions.put("phonenumber", "%"+ phoneNumber +"%");
        
        conditions.put("role", -1);
        
        return dao.search(conditions, true);
    }
    
    public List<Customer> showCustomer() {
        HashMap<String, Object> conditions = new HashMap();
        conditions.put("role", -1);
        
        return dao.search(conditions, false);
    }
    
    public boolean addCustomer(Customer customer) {
        customer.setRole(-1);
        
        return (dao.insert(customer) != -1);
    }
}
