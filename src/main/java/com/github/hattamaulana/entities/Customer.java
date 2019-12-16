/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.entities;

/**
 *
 * @author ngub
 */
public class Customer extends User {
    private String address;
    private String phoneNumber;
    
    public Customer () {}

    public Customer(
            String nama, String address, String phoneNumber
    ) {
        super.setName(nama);
        
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Object getValueAt(int index) {
        switch(index) {
            case 0:
                return getId();
            case 1:
                return getName();
            case 2:
                return getAddress();
            case 3:
                return getPhoneNumber();
        }
        
        return null;
    }
}
