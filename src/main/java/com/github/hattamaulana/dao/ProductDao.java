/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;

import com.github.hattamaulana.project.uas.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngub
 */
public class ProductDao extends BaseDao<Product> {

    @Override
    protected String getTableName() {
        return "product";
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
    protected void setAtributes(Map<String, Object> attrs, Product item) {
        attrs.put("nama", item.getNama());
        attrs.put("jenis", item.getJenis());
        attrs.put("harga", item.getHarga());
    }

    @Override
    protected Product getItem(
            List<Product> items, ResultSet resultSet
    ) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getString("id"));
        product.setNama(resultSet.getString("nama"));
        product.setHarga(resultSet.getInt("harga"));
        product.setJenis(resultSet.getString("jenis"));
        
        return product;
    }
}
