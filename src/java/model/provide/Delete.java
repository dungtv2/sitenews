/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide;

import model.I.IDelete;

/**
 *
 * @author Truong Van Dung
 */
public class Delete {

    private IDelete delete;

    public Delete(IDelete delete) {
        this.delete = delete;
    }

    public void setUpdate(IDelete delete) {
        this.delete = delete;
    }

    public IDelete getUpdate() {
        return this.delete;
    }

    public int delete(Object ob) {
        return delete.delete(ob);
    }
}
