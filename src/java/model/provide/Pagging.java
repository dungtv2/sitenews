/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide;

import java.util.List;
import model.I.IPagging;

/**
 *
 * @author Truong Van Dung
 */
public class Pagging<T> {

    private IPagging pagging;

    public Pagging(IPagging pagging) {
        this.pagging = pagging;
    }

    public List<T> pagging(int pageSize, int size) {
        return this.pagging.pagging(pageSize, size);
    }

    public IPagging getPagging() {
        return pagging;
    }

    public void setPagging(IPagging pagging) {
        this.pagging = pagging;
    }
}
