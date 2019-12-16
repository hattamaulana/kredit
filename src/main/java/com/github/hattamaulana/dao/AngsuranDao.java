/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;

import com.github.hattamaulana.project.uas.entities.Angsuran;
import com.github.hattamaulana.project.uas.entities.Transaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngub
 */
public class AngsuranDao extends BaseDao<Angsuran> {

    @Override
    protected String getTableName() {
        return "angsuran";
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
    protected void setAtributes(Map<String, Object> attrs, Angsuran item) {
        attrs.put("transaksi_id", item.getTransaction().getId());
        attrs.put("angsuran", item.getAngsuran());
    }

    @Override
    protected Angsuran getItem(
            List<Angsuran> items, ResultSet resultSet
    ) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("transaksi.id"));
        transaction.setTipeAngsuran(resultSet.getString("transaksi.tipe_angsuran"));
        
        Angsuran angsuran = new Angsuran();
        angsuran.setId(resultSet.getString("angsuran.id"));
        angsuran.setAngsuran(resultSet.getInt("angsuran.angsuran"));
        angsuran.setTransaction(transaction);
        
        return angsuran;
    }

    @Override
    protected String getJoinedTables() {
        return " angsuran join transaksi on angsuran.transaksi_id = transaksi.id";
    }
    
}
