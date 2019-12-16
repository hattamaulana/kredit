/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.entities;

import com.github.hattamaulana.lib.IUser;
import com.github.hattamaulana.ui.OwnerUI;

/**
 *
 * @author ngub
 */
public class Owner extends User implements IUser {
    
    @Override
    public void redirectToView(){
        new OwnerUI().setVisible(true);
    }
}
