/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;


import com.github.hattamaulana.entities.Customer;
import com.github.hattamaulana.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngub
 */
public class CustomerDao extends UserDao<Customer> {
    
    @Override
    protected void setAtributes(
            Map<String, Object> attrs, Customer item
    ) {
        super.setAtributes(attrs, item);
        
        attrs.put("address", item.getAddress());
        attrs.put("phonenumber", item.getPhoneNumber());
        attrs.put("role", item.getRole());
    }

    @Override
    protected Customer getItem(
            List<Customer> items, ResultSet resultSet
    ) throws SQLException {
        Customer customer = new Customer();
        
        customer.setId(resultSet.getString("id"));
        customer.setName(resultSet.getString("name"));
        customer.setAddress(resultSet.getString("address"));
        customer.setPhoneNumber(resultSet.getString("phonenumber"));
        
        return customer;
    }
}
