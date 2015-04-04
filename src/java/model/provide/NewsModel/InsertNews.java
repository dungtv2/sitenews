/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.NewsModel;

import entities.news;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import model.ConnectionManager;
import model.I.IInsert;

/**
 *
 * @author Truong Van Dung
 */
public class InsertNews implements IInsert {

    private ConnectionManager connectManager;
    private Connection con;

    public InsertNews() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int insert(Object ob) {
        int check = 0;
        news news = (news) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_insertNews(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, news.getNews_title());
            cs.setString(2, news.getNews_alias());
            cs.setString(3, news.getNews_url());
            cs.setString(4, news.getNews_img_sprite());
            cs.setString(5, news.getNews_file_vtt());
            cs.setString(6, news.getNews_author());
            cs.setString(7, news.getNews_source());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("EEEE,dd/MM/yyyy | HH:mm:ss z", new Locale("vi"));
            sim.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            cs.setString(8, sim.format(date));
            cs.setString(9, news.getNews_infor());
            cs.setString(10, news.getNews_full());
            cs.setString(11, news.getNews_check());
            cs.setString(12, news.getNews_img());
            cs.setInt(13, news.getNews_view());
            cs.setString(14, news.getNews_taglist());
            cs.setString(15, news.getNews_keyword());
            cs.setInt(16, news.getU_id());
            cs.setString(17, news.getType_id());
            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }
   
}
