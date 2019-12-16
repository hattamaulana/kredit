/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.hattamaulana.project.uas.entities;

import com.github.hattamaulana.project.uas.lib.IUser;
import com.github.hattamaulana.project.uas.ui.OwnerUI;

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
