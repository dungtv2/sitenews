/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide;

import java.util.List;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class Select<T> {

    private ISelect select;

    public Select(ISelect select) {
        this.select = select;
    }

    public void setSelect(ISelect select) {
        this.select = select;
    }

    public ISelect getSelect() {
        return this.select;
    }

    public List<T> select(T t) {
        return this.select.select(t);
    }
}
