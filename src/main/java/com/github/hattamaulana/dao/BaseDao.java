/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.dao;

import com.github.hattamaulana.lib.ConnectionFactory;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngub
 */
public abstract class BaseDao<T> {
    protected abstract String getTableName();
    protected abstract String getId();
    protected abstract String getPrimaryKey();
    protected abstract void setAtributes(Map<String, Object> attrs, T item);
    protected abstract T getItem
        (List<T> items, ResultSet resultSet) throws SQLException;
        
    protected String getJoinedTables() {
        return getTableName();
    }
    
    private static String join(Collection<String> values, String delimiter) {
        return join(values, "", delimiter);
    }
    
    private static String join(
            Collection<String> values, String optional, String delimiter
    ) {
        StringBuilder sb = new StringBuilder();
        String loopDelimiter = "";
        
        for (String o: values) {
            sb.append(loopDelimiter);
            sb.append(o).append(optional);
            
            loopDelimiter = delimiter;
        }
        
        return sb.toString();
    }
    
    private String questionMarks(int length) {
        StringBuilder sb = new StringBuilder();
        String loopDelimiter = "";
        
        for(int i=0; i < length; i++) {
            sb.append(loopDelimiter);
            sb.append("?");
            loopDelimiter=",";
        }
        
        return sb.toString();
    }
    
    public List<T> all() {
        return search(null);
    }
    
    public T getById(Object id) {
        HashMap<String, Object> conditions = new HashMap();
        conditions.put(getId(), id);
        
        return search(conditions).get(0);
    }
    
    public List<T> search(Map<String, Object> conditions) {
        return search(conditions, false);
    }
    
    public List<T> search(Map<String, Object> conditions, boolean useLike) {
        List<T> items = null;
        
        try {
            String operator = (useLike) ? " like ? " : " = ?";
            String where = (conditions != null) ?
                    join(conditions.keySet(), operator, " and ") : "1";
            String sql = String.format (
                    "select * from %s where %s", getJoinedTables(), where);
            
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            if (conditions != null) {
                Collection<Object> values = conditions.values();
                
                int i = 1;
                for(Object o: values) {
                    statement.setObject(i, o);
                    i++;
                }
            }
            
            System.out.println("Log: Query="+ statement.toString());
            
            ResultSet resultset = statement.executeQuery();
            items = new ArrayList<>();
            
            while(resultset.next()) {
                T item = getItem(items, resultset);
                items.add(item);
            }
            
            connection.close();
            System.out.println("Log: * OK");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    public int insert(T item) {
        Map<String, Object> attrs = new HashMap<>();
        setAtributes(attrs, item);
        
        return insert(attrs);
    }
    
    public int insert(Map<String, Object> attrs) {
        int result = -1;
        
        try {
            Set<String> keys = attrs.keySet();
            Collection<Object> values = attrs.values();
            
            String fields = join(keys, ",");
            String params = questionMarks(keys.size());
            String sql = String.format(
                    "insert into %s (%s) values (%s)", 
                    getTableName(), 
                    fields, 
                    params
            );
            
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            int i = 1;
            for (Map.Entry<String, Object> e : attrs.entrySet()) {
                statement.setObject(i, e.getValue());
                i++;
            }
            
            result = statement.executeUpdate();
            
            connection.close();
            System.out.println("Log: * OK");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public int update(T item) {
        Map<String, Object> attrs = new HashMap<>();
        setAtributes(attrs, item);
        int result = -1;
        
        try {
            attrs.remove(getId());
            Set<String> keys = attrs.keySet();
            String fields = join(keys, "=?", ",");
            String sql = String.format("update %s set %s where %s = ?",
                    getTableName(), fields, getId());
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            int i = 1;
            for (Map.Entry<String, Object> e : attrs.entrySet()) {
                statement.setObject(i, e.getValue());
                i++;
            }
            
            Class itemClass = item.getClass();
            Object key = itemClass.getDeclaredMethod(getPrimaryKey()).invoke(item);
            
            statement.setObject(i, key);
            
            System.out.println("Log : Query = "+ statement.toString());
            result = statement.executeUpdate();
            
            connection.close();
            System.out.println("Log: * OK");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseDao.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseDao.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public int delete (T item) {
        int result = -1;
        
        try {
            String sql = String.format(
                    "delete from %s where %s = ?",
                    getTableName(), 
                    getId()
            );
            
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            Class itemClass = item.getClass();
            Object key = itemClass.getMethod(getPrimaryKey()).invoke(item);
            
            statement.setObject(1, key);
            
            result = statement.executeUpdate();
            
            connection.close();
            System.out.println("Log: * OK");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseDao.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseDao.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
