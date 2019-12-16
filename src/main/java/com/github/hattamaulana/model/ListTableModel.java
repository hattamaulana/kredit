/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ngub
 * @param <T>
 */
public class ListTableModel<T extends BaseEntity> extends AbstractTableModel {
    
    private List<T> items;
    private String[] headers;

    public ListTableModel() { }

    public ListTableModel(String[] headers) {
        this.items = items;
        this.headers = headers;
    }

    @Override
    public int getRowCount() {
        return (items != null) ? items.size() : 0;
    }

    public T getItem(int index) {
        return items.get(index);
    }

    public void setItems(List<T> items) {
        this.items = items;
        this.fireTableDataChanged();
    }
    
    public List<T> getItems() {
        return items;
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int i) {
        return headers[i];
    }
    
    @Override
    public Object getValueAt(int i, int i1) {
        BaseEntity item = items.get(i);
        return item.getValueAt(i1);
    }
    
}
