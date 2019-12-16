/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;

import com.github.hattamaulana.entities.Customer;
import com.github.hattamaulana.entities.Product;
import com.github.hattamaulana.entities.Transaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngub
 */
public class TransactionDao extends BaseDao<Transaction> {
    @Override
    protected String getTableName() {
        return "transaksi";
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    protected String getPrimaryKey() {
        return "getId";
    }

    @Override
    protected void setAtributes(Map<String, Object> attrs, Transaction item) {
        attrs.put("product_id", item.getProduct().getId());
        attrs.put("customer", item.getCustomer().getId());
        attrs.put("tipe_angsuran", item.getTipeAngsuran());
        attrs.put("status", item.getStatus() == null ? 0 : 1);
    }

    @Override
    protected Transaction getItem(
            List<Transaction> items, ResultSet resultSet
    ) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getString("product.id"));
        product.setNama(resultSet.getString("product.nama"));
        product.setHarga(resultSet.getInt("product.harga"));
        product.setJenis(resultSet.getString("product.jenis"));
        
        Customer customer = new Customer();
        customer.setId(resultSet.getString("users.id"));
        customer.setName(resultSet.getString("users.name"));
        customer.setAddress(resultSet.getString("users.address"));
        customer.setPhoneNumber(resultSet.getString("users.phonenumber"));
        
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("transaksi.id"));
        transaction.setTipeAngsuran(resultSet.getString("transaksi.tipe_angsuran"));
        transaction.setDate(resultSet.getString("date"));
        transaction.setProduct(product);
        transaction.setCustomer(customer);
        
        int status = resultSet.getInt("status");
        transaction.setStatus(status != 0 ? "Sudah Lunas" : "Belum Lunas");
        
        return transaction;
    }

    @Override
    protected String getJoinedTables() {
        return " transaksi join users "
                + "on transaksi.customer = users.id "
                + "join product "
                + "on transaksi.product_id = product.id ";
    }
}
