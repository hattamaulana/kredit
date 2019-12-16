/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.controller;

import com.github.hattamaulana.dao.EmployeeDao;
import com.github.hattamaulana.dao.UserDao;
import com.github.hattamaulana.project.uas.entities.Employee;
import com.github.hattamaulana.project.uas.entities.Owner;
import com.github.hattamaulana.project.uas.entities.User;
import com.github.hattamaulana.project.uas.lib.IUser;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ngub
 */
public class AuthController {
    
    public boolean login(String username, String password) {
        UserDao dao = new UserDao();
        
        HashMap<String, Object> conditions = new HashMap();
        conditions.put("username", username);
        conditions.put("password", password);
        
        List<User> users = dao.search(conditions, true);
        
        if (users.size() == 1) {
            if (users.get(0).getRole() == 1) {
                Owner owner = new Owner();
                owner.redirectToView();
            } else if (users.get(0).getRole() == 0) {
                EmployeeDao employeeDao = new EmployeeDao();
                Employee employee = employeeDao.getById(users.get(0).getId());
                
                employee.redirectToView();
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean registerEmployee(Employee user) {
        EmployeeDao dao = new EmployeeDao();
        int result      = dao.insert(user);
        
        return (result != -1);
    }
}
