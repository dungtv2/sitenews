/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.NewsModel;

import entities.news;
import java.util.List;

/**
 *
 * @author Truong Van Dung
 */
public class ChainSearchNews {

    public AbstractSearchByAll chain() {
        AbstractSearchByAll id = new SelectNewsByNewsID();
        AbstractSearchByAll title = new SelectNewsByTitle();
        AbstractSearchByAll date = new SelectNewsByDate();
        AbstractSearchByAll key = new SelectNewsByKey();
        AbstractSearchByAll tag = new SelectNewsByTag();
        AbstractSearchByAll type = new SelectNewsByTypeId();
        AbstractSearchByAll user = new SelectNewsByUser();
        AbstractSearchByAll view = new SelectNewsByView();

        id.setNextSearch(title);
        title.setNextSearch(date);
        date.setNextSearch(key);
        key.setNextSearch(tag);
        tag.setNextSearch(type);
        type.setNextSearch(user);
        user.setNextSearch(view);
        return id;
    }
}
