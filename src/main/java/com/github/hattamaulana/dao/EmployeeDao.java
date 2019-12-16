/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;

import com.github.hattamaulana.project.uas.entities.Customer;
import com.github.hattamaulana.project.uas.entities.Employee;
import com.github.hattamaulana.project.uas.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngub
 */
public class EmployeeDao extends UserDao<Employee> {
    
    @Override
    protected void setAtributes(Map<String, Object> attrs, Employee item) {
        super.setAtributes(attrs, item);
        
        attrs.put("name", item.getName());
        attrs.put("username", item.getUsername());
        attrs.put("password", item.getPassword());
        attrs.put("role", item.getRole());
        attrs.put("salary", item.getSalary());
    }

    @Override
    protected Employee getItem(
            List<Employee> items, ResultSet resultSet
    ) throws SQLException {
        Employee employee = new Employee();
        
        employee.setId(resultSet.getString("id"));
        employee.setName(resultSet.getString("name"));
        employee.setUsername(resultSet.getString("username"));
        employee.setPassword(resultSet.getString("password"));
        employee.setAddress(resultSet.getString("address"));
        employee.setPhoneNumber(resultSet.getString("phonenumber"));
        employee.setSalary(resultSet.getFloat("salary"));
        employee.setHireDate(resultSet.getString("hire_date"));
        
        return employee;
    }
}
