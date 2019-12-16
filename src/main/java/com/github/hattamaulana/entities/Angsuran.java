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
public class Angsuran {
    private String id;
    private Transaction transaction;
    private int angsuran;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public int getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(int angsuran) {
        this.angsuran = angsuran;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
