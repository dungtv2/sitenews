/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide;

import model.I.IInsert;

/**
 *
 * @author Truong Van Dung
 */
public class Insert {

    private IInsert insert;

    public Insert(IInsert insert) {
        this.insert = insert;
    }

    public void setInsert(IInsert insert) {
        this.insert = insert;
    }

    public IInsert getInsert() {
        return this.insert;
    }

    public int insert(Object ob) {
        return insert.insert(ob);
    }
}
