/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.NewsModel;

import entities.news;
import java.util.ArrayList;
import java.util.List;
import model.provide.Select;

/**
 *
 * @author Truong Van Dung
 */
public abstract class AbstractSearchByAll {

    protected Select<news> select;
    private AbstractSearchByAll nextSearch;
    private static List<news> list = new ArrayList<news>();

    public AbstractSearchByAll(){
    }
    
    public void change(String news) {
        if (nextSearch != null) {
            this.nextSearch.search(news);
        }
    }

    public abstract void search(String news);

    public AbstractSearchByAll getNextSearch() {
        return nextSearch;
    }

    public void setNextSearch(AbstractSearchByAll nextSearch) {
        this.nextSearch = nextSearch;
    }

    public List<news> getList() {
        return list;
    }

    public void setList(List<news> list) {
        AbstractSearchByAll.list = list;
    }

}
