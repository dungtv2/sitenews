/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.UserModel;

import entities.user;
import java.util.ArrayList;
import java.util.List;
import model.provide.Select;

/**
 *
 * @author Truong Van Dung
 */
public abstract class AbstractSearchByAll {

    protected Select<user> select;
    private AbstractSearchByAll nextSearch;
    private static List<user> list = new ArrayList<user>();

    public AbstractSearchByAll() {
    }

    public void change(String user) {
        if (nextSearch != null) {
            this.nextSearch.search(user);
        }
    }

    public abstract void search(String user);

    public AbstractSearchByAll getNextSearch() {
        return nextSearch;
    }

    public void setNextSearch(AbstractSearchByAll nextSearch) {
        this.nextSearch = nextSearch;
    }

    public List<user> getList() {
        return list;
    }

    public void setList(List<user> list) {
        AbstractSearchByAll.list = list;
    }

}
