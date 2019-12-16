/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.project.uas.entities;

import com.github.hattamaulana.project.uas.lib.IUser;
import com.github.hattamaulana.project.uas.ui.EmployeeUI;

/**
 *
 * @author ngub
 */
public class Employee extends User implements IUser {
    private String address;
    private float salary;
    private String hireDate;
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void redirectToView() {
        new EmployeeUI().setVisible(true);
    }
}
