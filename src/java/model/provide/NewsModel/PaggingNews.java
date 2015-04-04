/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.NewsModel;

import entities.news;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import model.ConnectionManager;
import model.I.IPagging;
import model.provide.Pagging;

/**
 *
 * @author Truong Van Dung
 */
public class PaggingNews implements IPagging<news> {

    private ConnectionManager connectManager;
    private Connection con;

    {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<news> pagging(int page, int pageSize) {
        List<news> listNews = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            listNews = new ArrayList<news>();
            if (page == 1) {
                ps = con.prepareStatement("select top " + pageSize + " * from tbl_news inner join type_news on tbl_news.[type_id] = type_news.[type_id] inner join \n" +
"category on type_news.cate_id = category.cate_id inner join [user] on tbl_news.u_id = [user].u_id order by tbl_news.news_id");
                rs = ps.executeQuery();
            } else {
                int rowUsed = (page - 1) * pageSize;
                ps = con.prepareStatement("select top " + pageSize + " * from tbl_news inner join type_news on tbl_news.[type_id] = type_news.[type_id] inner join \n" +
"category on type_news.cate_id = category.cate_id inner join [user] on tbl_news.u_id = [user].u_id where tbl_news.news_id not in(select top " + rowUsed + " tbl_news.news_id from tbl_news inner join type_news on tbl_news.[type_id] = type_news.[type_id] inner join \n" +
"category on type_news.cate_id = category.cate_id inner join [user] on tbl_news.u_id = [user].u_id order by news_id)");
                rs = ps.executeQuery();
            }
            listNews = SelectCode.select(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectManager.closeConnection();
            return listNews;
        }
    }
}
