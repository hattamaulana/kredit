/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.entities;

import com.github.hattamaulana.model.BaseEntity;

/**
 *
 * @author ngub
 */
public class Transaction extends BaseEntity {
    private int id;
    private String date;
    private String tipeAngsuran;
    private Product product;
    private Customer customer;
    private String status;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTipeAngsuran() {
        return tipeAngsuran;
    }

    public void setTipeAngsuran(String tipeAngsuran) {
        this.tipeAngsuran = tipeAngsuran;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Object getValueAt(int index) {
        switch(index) {
            case 0:
                return getDate();
            case 1:
                return getProduct().getNama();
            case 2:
                return getTipeAngsuran();
            case 3:
                return getStatus();
            case 4: 
                return getProduct().getHarga();
        }
        
        return null;
    }
}
