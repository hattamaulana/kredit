/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.controller;

import com.github.hattamaulana.dao.AngsuranDao;
import com.github.hattamaulana.dao.TransactionDao;
import com.github.hattamaulana.project.uas.entities.Angsuran;
import com.github.hattamaulana.project.uas.entities.Transaction;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ngub
 */
public class TransactionController {
    private TransactionDao transaction = new TransactionDao();
    private AngsuranDao angsuran       = new AngsuranDao();
    
    public Transaction search(Object id) {
        HashMap<String, Object> conditions = new HashMap();
        conditions.put("transaksi.id", id);
        
        return transaction.search(conditions).get(0);
    }
    
    public List<Transaction> showHistoryTransaction(int custId) {
        HashMap<String, Object> conditions = new HashMap();
        conditions.put("customer", custId);
        
        return transaction.search(conditions);
    }
    
    public boolean create(Transaction param) {
        return transaction.insert(param) != -1;
    }
    
    public boolean setorAngsuran(Angsuran param) {
        if (angsuran.insert(param) != -1) {
            Transaction transaksi = param.getTransaction();
        
            HashMap<String, Object> conditions = new HashMap();
            conditions.put("transaksi_id", transaksi.getId());

            List<Angsuran> angsur = angsuran.search(conditions);

            int total = 0;
            for (Angsuran a: angsur)
                total += a.getAngsuran();

            System.out.println("Total = "+ total);
            System.out.println("Harga = "+ transaksi.getProduct().getHarga());
            
            System.out.println("> "+ (total >= transaksi.getProduct().getHarga()));
            
            if (total >= transaksi.getProduct().getHarga()) {
                transaksi.setStatus("1");

                transaction.update(transaksi);
            }
            
            return true;
        }
        
        return false;
    }
    
}
