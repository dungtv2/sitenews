/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide;

import model.I.IUpdate;

/**
 *
 * @author Truong Van Dung
 */
public class Update {

    private IUpdate update;

    public Update(IUpdate update) {
        this.update = update;
    }

    public void setUpdate(IUpdate update) {
        this.update = update;
    }

    public IUpdate getUpdate() {
        return this.update;
    }

    public int update(Object ob) {
        return update.update(ob);
    }
}
