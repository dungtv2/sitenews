/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.NewsModel;

import entities.news;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import model.ConnectionManager;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class SelectNewsByKey extends AbstractSearchByAll implements ISelect<news> {

    private ConnectionManager connectManager;
    private Connection con;

    public SelectNewsByKey() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<news> select(news t) {
        List<news> list = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            list = new ArrayList<news>();
            CallableStatement cs = con.prepareCall("{call sp_searchNewsByKey(?)}");
            cs.setString(1, t.getNews_keyword());
            ResultSet rs = cs.executeQuery();
            list = SelectCode.select(rs);
        } catch (Exception e) {
        } finally {
            connectManager.closeConnection();
            return list;
        }
    }

    @Override
    public void search(String str) {
        news news = new news();
        news.setNews_keyword(str);
        if (select(news) != null && select(news).size() > 0) {
            super.setList(select(news));
            super.setNextSearch(null);
        } else {
            super.change(str);
        }
    }
}
