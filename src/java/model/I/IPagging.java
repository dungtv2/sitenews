/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.I;

import java.util.List;

/**
 *
 * @author Truong Van Dung
 */
public interface IPagging<T> {
    public List<T> pagging(int pageSize, int size);
}
