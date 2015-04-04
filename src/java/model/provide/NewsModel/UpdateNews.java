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
import model.ConnectionManager;
import model.I.IUpdate;
import model.provide.Update;

/**
 *
 * @author Truong Van Dung
 */
public class UpdateNews implements IUpdate {

    private ConnectionManager connectManager;
    private Connection con;

    public UpdateNews() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int update(Object ob) {
        int iCheck = -1;
        news news = (news) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_updateNews(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, news.getNews_id());
            cs.setString(2, news.getNews_title());
            cs.setString(3, news.getNews_alias());
            cs.setString(4, news.getNews_url());
            cs.setString(5, news.getNews_img_sprite());
            cs.setString(6, news.getNews_file_vtt());
            cs.setString(7, news.getNews_author());
            cs.setString(8, news.getNews_source());
            cs.setString(9, news.getNews_infor());
            cs.setString(10, news.getNews_full());
            cs.setString(11, news.getNews_check());
            cs.setString(12, news.getNews_img());
            cs.setInt(13, news.getNews_view());
            cs.setString(14, news.getNews_taglist());
            cs.setString(15, news.getNews_keyword());
            cs.setInt(16, news.getU_id());
            cs.setString(17, news.getType_id());
            iCheck = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectManager.closeConnection();
            return iCheck;
        }
    }
//    public static void main(String[] args) {
//        Update update = new Update(new UpdateNews());
//        news news = new news();
//        news.setNews_id(2);
//        news.setNews_title("dung dai ca");
//        news.setNews_alias("dung-dai-ca");
//        news.setNews_url("abc");
//        news.setNews_img_sprite("s");
//        news.setNews_file_vtt("f");
//        news.setNews_author("truong dung");
//        news.setNews_date("");
//        news.setNews_source("");
//        news.setNews_full("");
//        news.setNews_infor("");
//        news.setNews_img("");
//        news.setNews_view(1);
//        news.setNews_taglist("");
//        news.setNews_keyword("");
//        news.setNews_check("adf");
//        System.out.println(update.update(news));
//    }
}
