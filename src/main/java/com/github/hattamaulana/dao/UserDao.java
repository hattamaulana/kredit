/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;

import com.github.hattamaulana.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngub
 */
public class UserDao<T extends User> extends BaseDao<T> {

    @Override
    protected String getTableName() {
        return "users";
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
    protected void setAtributes(Map<String, Object> attrs, T item) {
        attrs.put("name", item.getName());
        attrs.put("username", item.getUsername());
        attrs.put("password", item.getPassword());
    }

    @Override
    protected T getItem (
            List<T> items, ResultSet resultSet
    ) throws SQLException {
        T t = (T) new User();
        
        t.setId(resultSet.getString("id"));
        t.setName(resultSet.getString("name"));
        t.setUsername(resultSet.getString("username"));
        t.setPassword(resultSet.getString("password"));
        t.setRole(resultSet.getInt("role"));
        
        return t;
    }
    
}
